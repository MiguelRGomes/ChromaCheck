package br.com.java.tcc.application.adresses;

import br.com.java.tcc.application.adresses.resources.AdressRequest;
import br.com.java.tcc.application.adresses.resources.AdressResponse;

import java.util.List;

public interface AdressService {

    AdressResponse findById(Long id);

    List<AdressResponse> findAll();

    AdressResponse register(AdressRequest adressDTO);

    AdressResponse update(Long id, AdressRequest adressDTO);

    String delete(Long id);
}
