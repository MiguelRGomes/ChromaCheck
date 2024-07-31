package br.com.java.tcc.application.budget_products.resources;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import lombok.Getter;

import java.util.Date;

@Getter
public class BudgetProductRequest {
    private BudgetEntity budgetEntity;

    private ProductEntity productEntity;

    private Float quantity;

    private Float unit_price;

    private Float total;

    private Boolean approval;
}
