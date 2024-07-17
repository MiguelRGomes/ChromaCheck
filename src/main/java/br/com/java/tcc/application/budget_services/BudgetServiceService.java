package br.com.java.tcc.application.budget_services;

import br.com.java.tcc.application.budget_services.resources.BudgetServiceRequest;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceResponse;

import java.util.List;

public interface BudgetServiceService {

    BudgetServiceResponse findById(Long id);

    List<BudgetServiceResponse> findAll();

    BudgetServiceResponse register(BudgetServiceRequest budgetServiceDTO);

    BudgetServiceResponse update(Long id, BudgetServiceRequest budgetServiceDTO);

    String delete(Long id);
}
