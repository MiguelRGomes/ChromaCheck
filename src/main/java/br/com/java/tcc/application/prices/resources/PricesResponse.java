package br.com.java.tcc.application.prices.resources;

import br.com.java.tcc.application.prices.persistence.PricesEntity;
import lombok.Getter;

@Getter
public class PricesResponse {
    private Long id;

    private String name;

    private Float square_meter;

    private Float fixed_price;

    public PricesResponse(PricesEntity pricesEntity){
        this.id = pricesEntity.getId();
        this.name = pricesEntity.getName();
        this.square_meter = pricesEntity.getSquare_meter();
        this.fixed_price = pricesEntity.getFixed_price();
    }
}
