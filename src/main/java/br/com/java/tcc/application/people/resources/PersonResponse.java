package br.com.java.tcc.application.people.resources;


import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
        personEntity.getCompanyEntity().getId();
        //this.companyEntity = personEntity.getCompanyEntity();
        this.type = personEntity.getType();
        this.name = personEntity.getName();
        this.cpf_cnpj = personEntity.getCpf_cnpj();
        this.fone = personEntity.getFone();
        this.email = personEntity.getEmail();
    }
}