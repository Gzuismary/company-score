package com.serasa.companyscore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Company {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @NonNull
   private String name;
   @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
   private List<FinancialData> financialData = new ArrayList<FinancialData>();
}
