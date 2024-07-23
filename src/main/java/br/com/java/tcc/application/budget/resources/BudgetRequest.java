package br.com.java.tcc.application.budget.resources;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import lombok.Getter;

import java.util.Date;

@Getter
public class BudgetRequest {
    private CompanyEntity companyEntity;

    private PersonEntity personEntity;

    private AdressEntity adressEntity;

    private Date creation_date;

    private Date expiration_date;

    private String approval;

    private Float total;
}
