package br.com.java.tcc.application.adresses.util;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.resources.AdressRequest;
import br.com.java.tcc.application.adresses.resources.AdressResponse;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdressMapper {

    public AdressEntity toAdress(AdressRequest adressDTO){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(adressDTO.getPersonEntity().getId());

        return AdressEntity.builder()
                .personEntity(personEntity)
                .adress(adressDTO.getAdress())
                .number(adressDTO.getNumber())
                .district(adressDTO.getDistrict())
                .cep(adressDTO.getCep())
                .city(adressDTO.getCity())
                .uf(adressDTO.getUf())
                .build();
    }

    public AdressResponse toAdressDTO(AdressEntity adressEntity){
        return new AdressResponse(adressEntity);
    }

    public List<AdressResponse> toAdressDTO(List<AdressEntity> adressList){
        return adressList.stream().map(AdressResponse::new).collect(Collectors.toList());
    }

    public void updateAdressData(AdressEntity adressEntity, AdressRequest adressDTO){
        if (adressDTO.getAdress() != null) {
            adressEntity.setAdress(adressDTO.getAdress());
        }
        if (adressDTO.getNumber() != null) {
            adressEntity.setNumber(adressDTO.getNumber());
        }
        if (adressDTO.getDistrict() != null) {
            adressEntity.setDistrict(adressDTO.getDistrict());
        }
        if (adressDTO.getCep() != null) {
            adressEntity.setCep(adressDTO.getCep());
        }
        if (adressDTO.getCity() != null) {
            adressEntity.setCity(adressDTO.getCity());
        }
        if (adressDTO.getUf() != null) {
            adressEntity.setUf(adressDTO.getUf());
        }
    }
}
