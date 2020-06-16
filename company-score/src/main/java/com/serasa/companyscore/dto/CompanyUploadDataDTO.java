package com.serasa.companyscore.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class CompanyUploadDataDTO {
    @NotNull
    private MultipartFile file;
    @NotNull
    private Long companyId;
}
