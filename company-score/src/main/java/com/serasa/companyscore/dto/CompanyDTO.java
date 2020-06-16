package com.serasa.companyscore.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompanyDTO {
    @NotNull
    private String name;
}
