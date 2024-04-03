package br.com.java.tcc.application.services;

import br.com.java.tcc.application.services.persistence.ServicesEntity;
import br.com.java.tcc.application.services.persistence.ServicesRepository;
import br.com.java.tcc.application.services.resources.ServicesRequest;
import br.com.java.tcc.application.services.resources.ServicesResponse;
import br.com.java.tcc.application.services.util.ServicesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    private final ServicesMapper servicesMapper;

    @Override
    public ServicesResponse findById(Long id) {

        return servicesMapper.toServicesDTO(returnServices(id));
    }

    @Override
    public List<ServicesResponse> findAll(){
        return servicesMapper.toServicesDTO(servicesRepository.findAll());
    }

    @Override
    public ServicesResponse register(ServicesRequest servicesDTO){

        ServicesEntity servicesEntity = servicesMapper.toServices(servicesDTO);

        return servicesMapper.toServicesDTO(servicesRepository.save(servicesEntity));
    }

    @Override
    public ServicesResponse update(Long id, ServicesRequest servicesDTO){

        ServicesEntity servicesEntity = returnServices(id);
        servicesMapper.updateServicesData(servicesEntity,servicesDTO);
        return servicesMapper.toServicesDTO(servicesRepository.save(servicesEntity));
    }

    @Override
    public  String delete(Long id){
        servicesRepository.deleteById(id);
        return "Services id: " + id + " deleted";
    }

    private ServicesEntity returnServices(Long id) {
        return servicesRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Services wasn't found on database"));
    }
}
