package br.com.java.tcc.application.people.resources;


import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import lombok.Getter;

@Getter
public class PersonResponse {
    private Long id;

    private CompanyEntity companyEntity;

    private String type;

    private String name;

    private String cpf_cnpj;

    private String fone;

    private String email;

    public PersonResponse(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.companyEntity = personEntity.getCompanyEntity();
        this.type = personEntity.getType();
        this.name = personEntity.getName();
        this.cpf_cnpj = personEntity.getCpf_cnpj();
        this.fone = personEntity.getFone();
        this.email = personEntity.getEmail();
    }


}