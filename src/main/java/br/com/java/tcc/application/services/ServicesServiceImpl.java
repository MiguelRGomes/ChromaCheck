package br.com.java.tcc.application.services;

import br.com.java.tcc.application.company.CompanyService;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import br.com.java.tcc.application.services.persistence.ServicesRepository;
import br.com.java.tcc.application.services.resources.ServicesRequest;
import br.com.java.tcc.application.services.resources.ServicesResponse;
import br.com.java.tcc.application.services.util.ServicesMapper;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    MessageConfiguration messageConfiguration;

    private final ServicesRepository servicesRepository;

    private final ServicesMapper servicesMapper;

    private final CompanyService companyService;

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
        CompanyEntity companyEntity = companyService.returnCompany(servicesDTO.getCompanyEntity().getId());
        servicesEntity.setCompanyEntity(companyEntity);

        return servicesMapper.toServicesDTO(servicesRepository.save(servicesEntity));
    }

    @Override
    public ServicesResponse update(Long id, ServicesRequest servicesDTO){
        if (!servicesRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Serviço)"), HttpStatus.NOT_FOUND);
        }

        ServicesEntity servicesEntity = returnServices(id);
        servicesMapper.updateServicesData(servicesEntity,servicesDTO);
        return servicesMapper.toServicesDTO(servicesRepository.save(servicesEntity));
    }

    @Override
    public  String delete(Long id){
        if (!servicesRepository.existsById(id)){
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Serviço)"), HttpStatus.NOT_FOUND);
        }
        servicesRepository.deleteById(id);
        return "Serviço id: " + id + " deletado";
    }

    public ServicesEntity returnServices(Long id) {
        return servicesRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Serviço)"), HttpStatus.NOT_FOUND));
    }
}
