package br.com.java.tcc.application.prices;

import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.prices.resources.PricesRequest;
import br.com.java.tcc.application.prices.resources.PricesResponse;

import java.util.List;

public interface PricesService {
    PricesResponse findById(Long Id);

    PricesEntity returnPrices(Long Id);
    List<PricesResponse> findAll();

    PricesResponse register(PricesRequest pricesDTO);

    PricesResponse update(Long id, PricesRequest pricesDTO);

    String delete(Long id);

}
