package br.com.java.tcc.application.prices.resources;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class PricesRequest {
    private CompanyEntity companyEntity;

    private String name;

    private Float square_meter;

    private Float fixed_price;
}
