package br.com.java.tcc.application.people.resources;


import br.com.java.tcc.application.people.persistence.PersonEntity;
import lombok.Getter;

@Getter
public class PersonResponse {

    private Long id;

    private String type;

    private String name;


    private String cpf;

    private String fone;

    private String email;

    public PersonResponse(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.type = personEntity.getType();
        this.name = personEntity.getName();
        this.cpf = personEntity.getCpf();
        this.fone = personEntity.getFone();
        this.email = personEntity.getEmail();
    }


}