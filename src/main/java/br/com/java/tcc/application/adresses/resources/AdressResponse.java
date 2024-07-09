package br.com.java.tcc.application.adresses.resources;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import lombok.Getter;

@Getter
public class AdressResponse {
    private Long id;

    private PersonEntity personEntity;

    private String adress;

    private Integer number;

    private String district;

    private Integer cep;

    private String city;

    private String uf;

    public AdressResponse(AdressEntity adressEntity){
        this.id = adressEntity.getId();
        this.personEntity = adressEntity.getPersonEntity();
        this.adress = adressEntity.getAdress();
        this.number = adressEntity.getNumber();
        this.district = adressEntity.getDistrict();
        this.cep = adressEntity.getCep();
        this.city = adressEntity.getCity();
        this.uf = adressEntity.getUf();
    }
}
