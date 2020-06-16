package com.serasa.companyscore;

import com.serasa.companyscore.domain.Company;
import com.serasa.companyscore.domain.FinancialData;
import com.serasa.companyscore.dto.CompanyDTO;
import com.serasa.companyscore.dto.CompanyDataDTO;
import com.serasa.companyscore.enums.FinancialDataType;
import com.serasa.companyscore.service.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyServiceTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private CompanyService companyService;

    @Test
    public void testUploadFileSucces() throws URISyntaxException {
        FinancialData financialData = new FinancialData();
        financialData.setCompany(new Company());
        financialData.setFinancialDataId(1L);
        financialData.setId(1L);
        financialData.setType(FinancialDataType.INVOICE);

        List<FinancialData> financialDataList =
                new ArrayList(Arrays.asList(financialData, financialData, financialData));

        Double score = companyService.calculateCompanyScore(financialDataList);

        //Verify request succeed
        Assertions.assertEquals(53, score);
    }
}
