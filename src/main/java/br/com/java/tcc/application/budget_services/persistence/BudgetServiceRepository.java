package br.com.java.tcc.application.budget_services.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetServiceRepository extends JpaRepository<BudgetServiceEntity, Long> {

}
