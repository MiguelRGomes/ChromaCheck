package br.com.java.tcc.application.budget_services.resources;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import lombok.Getter;

import java.util.Date;

@Getter
public class BudgetServiceRequest {
    private BudgetEntity budgetEntity;

    private ServicesEntity servicesEntity;

    private PricesEntity pricesEntity;

    private Float quantity;

    private Float discount;
}
