package br.com.java.tcc.application.adresses.resources;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

@Getter
public class AdressRequest {
    private String adress;

    private Integer number;

    private String district;

    private Integer cep;

    private String city;

    private String uf;
}
