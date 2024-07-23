package br.com.java.tcc.application.products.resources;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import lombok.Getter;

@Getter
public class ProductRequest {
    private CompanyEntity companyEntity;

    private String name;

    private String description;
}
