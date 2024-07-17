package br.com.java.tcc.application.budget_products.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetProductRepository extends JpaRepository<BudgetProductEntity, Long> {

}
