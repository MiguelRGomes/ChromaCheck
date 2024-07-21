package br.com.java.tcc.application.services.resources;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import lombok.Getter;

@Getter
public class ServicesRequest {
    private CompanyEntity companyEntity;

    private String name;
}
