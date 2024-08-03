package br.com.java.tcc.application.people.util;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.resources.PersonRequest;
import br.com.java.tcc.application.people.resources.PersonResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public PersonEntity toPerson(PersonRequest personDTO){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(personDTO.getCompanyEntity().getId());

        return PersonEntity.builder()
                .companyEntity(companyEntity)
                .type(personDTO.getType())
                .name(personDTO.getName())
                .cpf_cnpj(personDTO.getCpf_cnpj())
                .fone(personDTO.getFone())
                .email(personDTO.getEmail())
                .build();
    }

    public PersonResponse toPersonDTO(PersonEntity personEntity){

        return new PersonResponse(personEntity);
    }

    public List<PersonResponse> toPeopleDTO(List<PersonEntity> peopleList){
        return peopleList.stream().map(PersonResponse::new).collect(Collectors.toList());
    }

    public void updatePersonData(PersonEntity personEntity, PersonRequest personDTO){
        if (personDTO.getCompanyEntity() != null) {
            personEntity.setCompanyEntity(personDTO.getCompanyEntity());
        }
        if (personDTO.getType() != null) {
            personEntity.setType(personDTO.getType());
        }
        if (personDTO.getName() != null) {
            personEntity.setName(personDTO.getName());
        }
        if (personDTO.getCpf_cnpj() != null) {
            personEntity.setCpf_cnpj(personDTO.getCpf_cnpj());
        }
        if (personDTO.getFone() != null) {
            personEntity.setFone(personDTO.getFone());
        }
        if (personDTO.getEmail() != null) {
            personEntity.setEmail(personDTO.getEmail());
        }
    }
}
