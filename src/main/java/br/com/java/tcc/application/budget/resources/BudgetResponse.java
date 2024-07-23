package br.com.java.tcc.application.budget.resources;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import lombok.Getter;

import java.util.Date;

@Getter
public class BudgetResponse {
    private Long id;

    private CompanyEntity companyEntity;

    private PersonEntity personEntity;

    private AdressEntity adressEntity;

    private Date creation_date;

    private Date expiration_date;

    private Boolean approval;

    private Float total;

    public BudgetResponse(BudgetEntity budgetEntity){
        this.id = budgetEntity.getId();
        this.companyEntity = budgetEntity.getCompanyEntity();
        this.personEntity = budgetEntity.getPersonEntity();
        this.adressEntity = budgetEntity.getAdressEntity();
        this.creation_date = budgetEntity.getCreation_date();
        this.expiration_date = budgetEntity.getExpiration_date();
        this.approval = budgetEntity.getApproval();
        this.total = budgetEntity.getTotal();
    }
}
