package br.com.java.tcc.application.resources;


import br.com.java.tcc.application.persistence.PersonEntity;
import com.almada.people.entity.Person;
import lombok.Getter;

@Getter
public class PersonResponse {

    private Long id;

    private String name;


    private String cpf;


    private Integer age;

    public PersonResponse(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.name = personEntity.getName();
        this.cpf = personEntity.getCpf();
        this.age = personEntity.getAge();
    }


}