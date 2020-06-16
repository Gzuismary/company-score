package com.serasa.companyscore.repository;

import com.serasa.companyscore.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
