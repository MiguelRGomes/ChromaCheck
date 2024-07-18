package br.com.java.tcc.application.company.resources;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import lombok.Getter;

@Getter
public class CompanyResponse {
    private Long id;

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

    public CompanyResponse(CompanyEntity companyEntity){
        this.id = companyEntity.getId();
        this.cnpj = companyEntity.getCnpj();
        this.name = companyEntity.getName();
        this.address = companyEntity.getAddress();
        this.number = companyEntity.getNumber();
        this.district = companyEntity.getDistrict();
        this.cep = companyEntity.getCep();
        this.city = companyEntity.getCity();
        this.uf = companyEntity.getUf();
        this.fone = companyEntity.getFone();
        this.email = companyEntity.getEmail();
    }

    public static CompanyResponse from(CompanyEntity companyEntity) {
        return new CompanyResponse(companyEntity);
    }
}
