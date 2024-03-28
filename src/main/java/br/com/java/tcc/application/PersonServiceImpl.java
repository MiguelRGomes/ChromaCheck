package br.com.java.tcc.application;

import br.com.java.tcc.application.persistence.PersonEntity;
import br.com.java.tcc.application.persistence.PersonRepository;
import br.com.java.tcc.application.resources.PersonRequest;
import br.com.java.tcc.application.resources.PersonResponse;
import br.com.java.tcc.application.util.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;


    @Override
    public PersonResponse findById(Long id) {
        return personMapper.toPersonDTO(returnPerson(id));
    }

    @Override
    public List<PersonResponse> findAll() {
        return personMapper.toPeopleDTO(personRepository.findAll());
    }

    @Override
    public PersonResponse register(PersonRequest personDTO) {

        PersonEntity personEntity = personMapper.toPerson(personDTO);

        return personMapper.toPersonDTO(personRepository.save(personEntity));
    }

    @Override
    public PersonResponse update(Long id, PersonRequest personDTO) {

        PersonEntity personEntity = returnPerson(id);

        personMapper.updatePersonData(personEntity,personDTO);

        return personMapper.toPersonDTO(personRepository.save(personEntity));
    }

    @Override
    public String delete(Long id) {
        personRepository.deleteById(id);
        return "Person id: "+id+" deleted";
    }

    private PersonEntity returnPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Person wasn't fount on database"));
    }

}