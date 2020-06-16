package com.serasa.companyscore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDataDTO {
    private Long id;
    private String name;
    private Double score;
}
