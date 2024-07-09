package br.com.java.tcc.application.adresses.util;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.resources.AdressRequest;
import br.com.java.tcc.application.adresses.resources.AdressResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdressMapper {

    public AdressEntity toAdress(AdressRequest adressDTO){
        return AdressEntity.builder()
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
        adressEntity.setAdress(adressDTO.getAdress());
        adressEntity.setNumber(adressDTO.getNumber());
        adressEntity.setDistrict(adressDTO.getDistrict());
        adressEntity.setCep(adressDTO.getCep());
        adressEntity.setCity(adressDTO.getCity());
        adressEntity.setUf(adressDTO.getUf());
    }
}
