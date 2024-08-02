package br.com.java.tcc.application.adresses;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.persistence.AdressRepository;
import br.com.java.tcc.application.adresses.resources.AdressRequest;
import br.com.java.tcc.application.adresses.resources.AdressResponse;
import br.com.java.tcc.application.adresses.util.AdressMapper;
import br.com.java.tcc.application.people.PersonService;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.persistence.PersonRepository;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Primary
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    @Autowired
    MessageConfiguration messageConfiguration;

    private final AdressRepository adressRepository;
    private final AdressMapper adressMapper;
    private final PersonService personService;

    @Override
    public AdressResponse findById(Long id) {

        return adressMapper.toAdressDTO(returnAdress(id));
    }

    @Override
    public List<AdressResponse> findAll() {
        return adressMapper.toAdressDTO(adressRepository.findAll());
    }

    @Override
    @Transactional
    public AdressResponse register(AdressRequest adressDTO) {
        AdressEntity adressEntity = adressMapper.toAdress(adressDTO);
        PersonEntity personEntity = personService.returnPerson(adressDTO.getPersonEntity().getId());
        adressEntity.setPersonEntity(personEntity);

        return adressMapper.toAdressDTO(adressRepository.save(adressEntity));
    }

    @Override
    public AdressResponse update(Long id, AdressRequest adressDTO) {
        if (!adressRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Endereço)"), HttpStatus.NOT_FOUND);
        }

        AdressEntity adressEntity = returnAdress(id);

        // Verifique se o valor de 'adress' no 'adressDTO' não é nulo
        if (adressDTO.getAdress() == null) {
            throw new CustomException("O campo 'adress' não pode ser nulo", HttpStatus.BAD_REQUEST);
        }

        adressMapper.updateAdressData(adressEntity, adressDTO);
        return adressMapper.toAdressDTO(adressRepository.save(adressEntity));
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
        return adressRepository.findById(id)
                //.orElseThrow(()-> new RuntimeException("Adress wasn't found on database"));
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Endereço)"), HttpStatus.NOT_FOUND));
    }
}
