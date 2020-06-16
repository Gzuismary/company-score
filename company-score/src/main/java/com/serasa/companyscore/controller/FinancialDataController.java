package com.serasa.companyscore.controller;

import com.serasa.companyscore.dto.CompanyUploadDataDTO;
import com.serasa.companyscore.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/financial-data")
@Validated
public class FinancialDataController {

    private final CompanyService companyService;
    @Autowired
    public FinancialDataController(
            CompanyService service
            ) {
        this.companyService = service;
    }

    @PostMapping
    public ResponseEntity uploadFile(@ModelAttribute @Valid CompanyUploadDataDTO companyUploadDataDTO) throws IOException {
        this.companyService.readFile(companyUploadDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
