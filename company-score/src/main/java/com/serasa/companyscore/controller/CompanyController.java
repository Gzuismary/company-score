package com.serasa.companyscore.controller;

import com.serasa.companyscore.dto.CompanyDTO;
import com.serasa.companyscore.dto.CompanyDataDTO;
import com.serasa.companyscore.repository.CompanyRepository;
import com.serasa.companyscore.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
@Validated
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(
            CompanyService companyService,
            CompanyRepository companyRepository
    ) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<CompanyDataDTO> getCompanies() {
        return this.companyService.generateRanking();
    }

}
