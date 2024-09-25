package br.com.java.tcc.application.budget_products.persistence;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetProductRepository extends JpaRepository<BudgetProductEntity, Long> {

    List<BudgetProductEntity> findByBudgetEntity(BudgetEntity budgetEntity);
}
