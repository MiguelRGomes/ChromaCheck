package br.com.java.tcc.application.budget_products.resources;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget_products.persistence.BudgetProductEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import lombok.Getter;

import java.util.Date;

@Getter
public class BudgetProductResponse {
    private Long id;

    private BudgetEntity budgetEntity;

    private ProductEntity productEntity;

    private Float quantity;

    private Float unit_price;

    private Float total;

    private Boolean approval;

    public BudgetProductResponse(BudgetProductEntity budgetProductEntity){
        this.id = budgetProductEntity.getId();
        this.budgetEntity = budgetProductEntity.getBudgetEntity();
        this.productEntity = budgetProductEntity.getProductEntity();
        this.quantity = budgetProductEntity.getQuantity();
        this.unit_price = budgetProductEntity.getUnit_price();
        this.total = budgetProductEntity.getTotal();
        this.approval = budgetProductEntity.getApproval();
    }
}
