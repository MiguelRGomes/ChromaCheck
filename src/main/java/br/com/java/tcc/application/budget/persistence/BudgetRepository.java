package br.com.java.tcc.application.budget.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {

    List<BudgetEntity> findByCompanyEntity(CompanyEntity companyEntity);
}
