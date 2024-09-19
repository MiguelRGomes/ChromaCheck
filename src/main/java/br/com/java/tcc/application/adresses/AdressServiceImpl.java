package br.com.java.tcc.application.adresses;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.persistence.AdressRepository;
import br.com.java.tcc.application.adresses.resources.AdressRequest;
import br.com.java.tcc.application.adresses.resources.AdressResponse;
import br.com.java.tcc.application.adresses.util.AdressMapper;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.PersonService;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    @Autowired
    MessageConfiguration messageConfiguration;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private AdressMapper adressMapper;

    @Autowired
    private PersonService personService;

    @Override
    public AdressResponse findById(Long id) {

        return adressMapper.toAdressDTO(returnAdress(id));
    }

    @Override
    public List<AdressResponse> findByPersonEntity(Long personId) {
        PersonEntity personEntity = personService.returnPerson(personId);
        List<AdressEntity> adress = adressRepository.findByPersonEntity(personEntity);
        return adressMapper.toAdressDTO(adress);
    }

    @Override
    @Transactional
    public AdressResponse register(AdressRequest adressDTO) {
        AdressEntity adressEntity = adressMapper.toAdress(adressDTO);
        PersonEntity personEntity = personService.returnPerson(adressDTO.getPersonEntity().getId());
        adressEntity.setPersonEntity(personEntity);

        if (adressDTO.getUf() != null && !isUfValid(adressDTO.getUf())) {
            throw new CustomException(
                    messageConfiguration.getMessageByCode(MessageCodeEnum.INVALID_STATE, adressDTO.getUf()),
                    HttpStatus.BAD_REQUEST);
        }

        return adressMapper.toAdressDTO(adressRepository.save(adressEntity));
    }

    @Override
    public AdressResponse update(Long id, AdressRequest adressDTO) {
        if (!adressRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Endereço)"), HttpStatus.NOT_FOUND);
        }

        AdressEntity adressEntity = returnAdress(id); // Busca a entidade existente
        adressMapper.updateAdressData(adressEntity, adressDTO); // Atualiza os campos conforme o DTO
        AdressEntity updatedEntity = adressRepository.save(adressEntity); // Salva a entidade atualizada
        return adressMapper.toAdressDTO(updatedEntity); // Retorna a resposta DT
    }

    @Override
    public String delete(Long id) {
        if (!adressRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Endereço)"), HttpStatus.NOT_FOUND);
        }

        adressRepository.deleteById(id);
        return "Endereço id: " + id + " deletado";
    }

    public AdressEntity returnAdress(Long id){
        return adressRepository.findById(id).orElseThrow(()->
                new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Endereço)"), HttpStatus.NOT_FOUND));
    }

    public boolean isUfValid(String uf) {
        String sql = "SELECT COUNT(*) FROM country_states WHERE state_code = ?";
        // Usando query() para obter uma lista de resultados, se necessário
        List<Integer> results = jdbcTemplate.query(sql, new Object[]{uf}, (rs, rowNum) -> rs.getInt(1));
        return !results.isEmpty() && results.get(0) > 0;
    }
}
