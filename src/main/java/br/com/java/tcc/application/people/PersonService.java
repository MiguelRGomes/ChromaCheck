package br.com.java.tcc.application.people;


import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.resources.PersonRequest;
import br.com.java.tcc.application.people.resources.PersonResponse;

import java.util.List;

public interface PersonService {

    PersonResponse findById(Long id);

    PersonEntity returnPerson(Long id);

    List<PersonResponse> findByCompanyEntity(Long companyId);

    PersonResponse register(PersonRequest personDTO);

    PersonResponse update(Long id, PersonRequest personDTO);

    String delete(Long id);

}