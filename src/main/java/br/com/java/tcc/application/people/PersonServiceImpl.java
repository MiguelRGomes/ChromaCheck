package br.com.java.tcc.application.people;

import br.com.java.tcc.application.company.CompanyService;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.persistence.PersonRepository;
import br.com.java.tcc.application.people.resources.PersonRequest;
import br.com.java.tcc.application.people.resources.PersonResponse;
import br.com.java.tcc.application.people.util.PersonMapper;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import br.com.java.tcc.util.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.java.tcc.util.Util;
import javax.validation.Valid;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    MessageConfiguration messageConfiguration;

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final CompanyService companyService;

    @Override
    public PersonResponse findById(Long id) {

        return personMapper.toPersonDTO(returnPerson(id));
    }

    @Override
    public List<PersonResponse> findByCompanyEntity(Long companyId) {
        // Filtra as pessoas pelo ID da empresa
        CompanyEntity companyEntity = companyService.returnCompany(companyId);
        List<PersonEntity> persons = personRepository.findByCompanyEntity(companyEntity);
        return personMapper.toPeopleDTO(persons);
    }

    @Override
    @Transactional
    public PersonResponse register(PersonRequest personDTO) {

        if(!Util.validationDocument(personDTO.getCpf_cnpj())){
            throw new RuntimeException("CPF/CNPj inválido");
        }

        if (!"C".equals(personDTO.getType()) && !"F".equals(personDTO.getType())) {
            throw new IllegalArgumentException("Type must be either 'C' or 'F'");
        }

        PersonEntity personEntity = personMapper.toPerson(personDTO);

        CompanyEntity companyEntity = companyService.returnCompany(personDTO.getCompanyEntity().getId());
        personEntity.setCompanyEntity(companyEntity);
        return personMapper.toPersonDTO(personRepository.save(personEntity));
    }

    @Override
    public PersonResponse update(Long id, PersonRequest personDTO) {
        if (!personRepository.existsById(id)){
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Pessoa)"), HttpStatus.NOT_FOUND);
        }

        PersonEntity personEntity = returnPerson(id);
        personMapper.updatePersonData(personEntity,personDTO);
        PersonEntity updateEntity = personRepository.save(personEntity);
        return personMapper.toPersonDTO(updateEntity);
    }

    @Override
    public String delete(Long id) {
        if (!personRepository.existsById(id)){
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Pessoa)"), HttpStatus.NOT_FOUND);
        }
        personRepository.deleteById(id);
        return "Cliente id: "+id+" deletado";
    }

    public PersonEntity returnPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Pessoa)"), HttpStatus.NOT_FOUND));
    }

}