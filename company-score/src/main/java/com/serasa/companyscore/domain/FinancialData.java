package com.serasa.companyscore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serasa.companyscore.enums.FinancialDataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FinancialData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Long financialDataId;
    @NonNull
    @Enumerated(EnumType.STRING)
    private FinancialDataType type;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Company company;
}
