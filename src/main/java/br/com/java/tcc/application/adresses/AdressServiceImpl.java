package br.com.java.tcc.application.adresses;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.persistence.AdressRepository;
import br.com.java.tcc.application.adresses.resources.AdressRequest;
import br.com.java.tcc.application.adresses.resources.AdressResponse;
import br.com.java.tcc.application.adresses.util.AdressMapper;
import br.com.java.tcc.application.people.PersonService;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.persistence.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Primary
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

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
        AdressEntity adressEntity = returnAdress(id);
        adressMapper.updateAdressData(adressEntity, adressDTO);
        return adressMapper.toAdressDTO(adressRepository.save(adressEntity));
    }

    @Override
    public String delete(Long id) {
        adressRepository .deleteById(id);
        return "Adress id: " + id + " deleted";
    }

    public AdressEntity returnAdress(Long id){
        return adressRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Adress wasn't found on database"));
    }
}
