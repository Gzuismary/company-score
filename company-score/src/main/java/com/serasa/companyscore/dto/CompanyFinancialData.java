package com.serasa.companyscore.dto;

import com.serasa.companyscore.enums.FinancialDataType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class CompanyFinancialData {
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private FinancialDataType type;
}
