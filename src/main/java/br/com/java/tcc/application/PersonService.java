package br.com.java.tcc.application;


import br.com.java.tcc.application.resources.PersonRequest;
import br.com.java.tcc.application.resources.PersonResponse;

import java.util.List;

public interface PersonService {

    PersonResponse findById(Long id);

    List<PersonResponse> findAll();

    PersonResponse register(PersonRequest personDTO);

    PersonResponse update(Long id, PersonRequest personDTO);

    String delete(Long id);

}