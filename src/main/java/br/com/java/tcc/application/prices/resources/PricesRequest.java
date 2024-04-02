package br.com.java.tcc.application.prices.resources;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class PricesRequest {
    private String name;

    private Float square_meter;

    private Float fixed_price;
}
