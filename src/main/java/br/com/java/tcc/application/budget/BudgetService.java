package br.com.java.tcc.application.budget;

import br.com.java.tcc.application.budget.resources.BudgetRequest;
import br.com.java.tcc.application.budget.resources.BudgetResponse;

import java.util.List;

public interface BudgetService {

    BudgetResponse findById(Long id);

    List<BudgetResponse> findAll();

    BudgetResponse register(BudgetRequest budgetDTO);

    BudgetResponse update(Long id, BudgetRequest budgetDTO);

    String delete(Long id);
}
