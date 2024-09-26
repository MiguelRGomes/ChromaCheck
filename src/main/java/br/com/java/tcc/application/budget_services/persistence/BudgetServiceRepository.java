package br.com.java.tcc.application.budget_services.persistence;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetServiceRepository extends JpaRepository<BudgetServiceEntity, Long> {

    List<BudgetServiceEntity> findByBudgetEntity(BudgetEntity budgetEntity);
}
