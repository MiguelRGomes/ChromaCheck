package br.com.java.tcc.application.people;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.people.PersonService;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.persistence.PersonRepository;
import br.com.java.tcc.application.people.resources.PersonRequest;
import br.com.java.tcc.application.people.resources.PersonResponse;
import br.com.java.tcc.application.people.util.PersonMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final CompanyRepository companyRepository;

    @Override
    public PersonResponse findById(Long id) {
        return personMapper.toPersonDTO(returnPerson(id));
    }

    @Override
    public List<PersonResponse> findAll() {

        return personMapper.toPeopleDTO(personRepository.findAll());
    }

    @Override
    @Transactional
    public PersonResponse register(@Valid PersonRequest personDTO) {

        if (!"C".equals(personDTO.getType()) && !"F".equals(personDTO.getType())) {
            throw new IllegalArgumentException("Type must be either 'C' or 'F'");
        }

        PersonEntity personEntity = personMapper.toPerson(personDTO);

        Optional<CompanyEntity> optional =  companyRepository.findById(personDTO.getCompanyEntity().getId());
        if (optional.isPresent()){
            CompanyEntity companyEntity = optional.get();
            personEntity.setCompanyEntity(companyEntity);
            return personMapper.toPersonDTO(personRepository.save(personEntity));
        }
        else {
            throw new RuntimeException("Company with id " + personDTO.getCompanyEntity().getId() + " not found");
        }
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