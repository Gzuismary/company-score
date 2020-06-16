package com.serasa.companyscore.repository;

import com.serasa.companyscore.domain.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialDataRepository extends JpaRepository<FinancialData, Long> {
}
