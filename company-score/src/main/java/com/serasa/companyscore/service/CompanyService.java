package com.serasa.companyscore.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.serasa.companyscore.domain.Company;
import com.serasa.companyscore.domain.FinancialData;
import com.serasa.companyscore.dto.CompanyDTO;
import com.serasa.companyscore.dto.CompanyDataDTO;
import com.serasa.companyscore.dto.CompanyFinancialData;
import com.serasa.companyscore.dto.CompanyUploadDataDTO;
import com.serasa.companyscore.enums.FinancialDataType;
import com.serasa.companyscore.repository.CompanyRepository;
import com.serasa.companyscore.repository.FinancialDataRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final FinancialDataRepository financialDataRepository;

    CompanyService(
            CompanyRepository companyRepository,
            FinancialDataRepository financialDataRepository
            ) {
        this.companyRepository = companyRepository;
        this.financialDataRepository = financialDataRepository;
    }

    @Transactional
    public void readFile(CompanyUploadDataDTO companyUploadDataDTO) throws IOException {
        Long companyId = companyUploadDataDTO.getCompanyId();
        Company company = this.companyRepository
                .findById(companyId)
                .orElseThrow(() -> new ObjectNotFoundException(companyId, "Company"));

        Reader reader = new InputStreamReader(companyUploadDataDTO.getFile().getInputStream());
        CsvToBean<CompanyFinancialData> csvCompanyFinancialData = new CsvToBeanBuilder(reader)
                .withType(CompanyFinancialData.class)
                .build();

        List<CompanyFinancialData> companyFinancialDataList = csvCompanyFinancialData.parse();

        List<FinancialData> financialData = new ArrayList();

        companyFinancialDataList.forEach(companyFinancialData -> {
            financialData.add(new FinancialData(companyFinancialData.getId(), companyFinancialData.getType(), company));
        });

        this.financialDataRepository.saveAll(financialData);
    }

    public void createCompany(CompanyDTO companyDTO) {
        Company company = new Company(companyDTO.getName());
        this.companyRepository.save(company);
    }

    public List<CompanyDataDTO> generateRanking() {
        List<Company> companies =  this.companyRepository.findAll();

        List<CompanyDataDTO> companyDataDTOS = new ArrayList();

        Double score;
        for(Company company : companies) {
            score = this.calculateCompanyScore(company.getFinancialData());

            companyDataDTOS
                    .add(new CompanyDataDTO(
                        company.getId(),
                        company.getName(),
                        score
                    )
            );
        }

        return companyDataDTOS;
    }

    public Double calculateCompanyScore(List<FinancialData> financialDataList) {
        Double score = 50.0;

        List<FinancialData> financialDataOrderedList = financialDataList.stream()
                .sorted(Comparator.comparing(FinancialData::getType))
                .collect(Collectors.toList());

        for (FinancialData financialData : financialDataOrderedList) {
            score = financialData.getType() == FinancialDataType.INVOICE
                    ? Math.floor(score + score * 0.02)
                    : Math.ceil(score - score * 0.04);
        }

        if (score > 100) {
            score = 100.0;
        }else if (score < 1) {
            score = 1.0;
        }

        return score;
    }
}
