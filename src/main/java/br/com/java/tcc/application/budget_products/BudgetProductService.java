package br.com.java.tcc.application.budget_products;

import br.com.java.tcc.application.budget_products.persistence.BudgetProductEntity;
import br.com.java.tcc.application.budget_products.resources.BudgetProductRequest;
import br.com.java.tcc.application.budget_products.resources.BudgetProductResponse;

import java.util.List;

public interface BudgetProductService {

    BudgetProductResponse findById(Long id);

    BudgetProductEntity returnBudgetProduct(Long id);

    BudgetProductResponse register(BudgetProductRequest budgetProductDTO);

    BudgetProductResponse update(Long id, BudgetProductRequest budgetProductDTO);

    String delete(Long id);

    List<BudgetProductResponse> findByBudgetEntity(Long budgetId);
}
