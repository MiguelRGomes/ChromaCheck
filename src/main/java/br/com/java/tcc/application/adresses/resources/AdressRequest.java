package br.com.java.tcc.application.adresses.resources;

import br.com.java.tcc.application.people.persistence.PersonEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

@Getter
public class AdressRequest {
    private PersonEntity personEntity;

    private String adress;

    private Integer number;

    private String district;

    private Integer cep;

    private String city;

    private String uf;
}
