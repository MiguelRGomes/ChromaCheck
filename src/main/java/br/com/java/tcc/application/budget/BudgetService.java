package br.com.java.tcc.application.budget;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget.resources.BudgetRequest;
import br.com.java.tcc.application.budget.resources.BudgetResponse;

import java.util.List;

public interface BudgetService {

    BudgetResponse findById(Long id);

    BudgetEntity returnBudget(Long id);

    BudgetResponse register(BudgetRequest budgetDTO);

    BudgetResponse update(Long id, BudgetRequest budgetDTO);

    String delete(Long id);

    List<BudgetResponse> findByCompanyEntity(Long companyId);
}
