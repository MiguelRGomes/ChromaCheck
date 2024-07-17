package br.com.java.tcc.application.budget.resources;

import lombok.Getter;

import java.util.Date;

@Getter
public class BudgetRequest {
    private Date creation_date;

    private Date expiration_date;

    private String approval;

    private Float total;
}
