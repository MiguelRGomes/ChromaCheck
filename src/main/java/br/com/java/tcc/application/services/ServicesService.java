package br.com.java.tcc.application.services;

import br.com.java.tcc.application.services.resources.ServicesRequest;
import br.com.java.tcc.application.services.resources.ServicesResponse;

import java.util.List;

public interface ServicesService {

    ServicesResponse findById(Long id);

    List<ServicesResponse> findAll();

    ServicesResponse register(ServicesRequest servicesDTO);

    ServicesResponse update(Long id, ServicesRequest servicesDTO);

    String delete(Long id);
}
