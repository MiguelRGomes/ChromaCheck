package br.com.java.tcc.application.company.resources;

import lombok.Getter;

@Getter
public class CompanyRequest {
    private String cnpj;

    private String name;

    private String address;

    private Integer number;

    private String district;

    private String cep;

    private String city;

    private String uf;

    private String fone;

    private String email;

    private String password;
}
