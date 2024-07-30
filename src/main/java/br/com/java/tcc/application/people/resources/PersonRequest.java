package br.com.java.tcc.application.people.resources;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.configuration.validation.CPFOrCNPJ.CPFOrCNPJ;
import lombok.Getter;

@Getter
public class PersonRequest {
    private CompanyEntity companyEntity;

    private String type;

    private String name;

    private String cpf_cnpj;

    private String fone;

    private String email;

}
