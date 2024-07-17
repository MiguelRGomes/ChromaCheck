package br.com.java.tcc.application.budget_services.resources;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget_services.persistence.BudgetServiceEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import lombok.Getter;

import java.util.Date;

@Getter
public class BudgetServiceResponse {
    private Long id;

    private BudgetEntity budgetEntity;

    private ServicesEntity servicesEntity;

    private PricesEntity pricesEntity;

    private Float quantity;

    private Float discount;

    public BudgetServiceResponse(BudgetServiceEntity budgetServiceEntity){
        this.id = budgetServiceEntity.getId();
        this.budgetEntity = budgetServiceEntity.getBudgetEntity();
        this.servicesEntity = budgetServiceEntity.getServicesEntity();
        this.pricesEntity = budgetServiceEntity.getPricesEntity();
        this.quantity = budgetServiceEntity.getQuantity();
        this.discount = budgetServiceEntity.getDiscount();
    }
}
