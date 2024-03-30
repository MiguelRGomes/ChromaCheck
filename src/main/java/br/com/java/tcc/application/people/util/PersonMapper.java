package br.com.java.tcc.application.people.util;

import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.resources.PersonRequest;
import br.com.java.tcc.application.people.resources.PersonResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public PersonEntity toPerson(PersonRequest personDTO){

        return PersonEntity.builder()
                .name(personDTO.getName())
                .cpf(personDTO.getCpf())
                .age(personDTO.getAge())
                .build();
    }

    public PersonResponse toPersonDTO(PersonEntity personEntity){

        return new PersonResponse(personEntity);
    }

    public List<PersonResponse> toPeopleDTO(List<PersonEntity> peopleList){
        return peopleList.stream().map(PersonResponse::new).collect(Collectors.toList());
    }

    public void updatePersonData(PersonEntity personEntity, PersonRequest personDTO){
        personEntity.setName(personDTO.getName());
        personEntity.setCpf(personDTO.getCpf());
        personEntity.setAge(personDTO.getAge());
    }
}
