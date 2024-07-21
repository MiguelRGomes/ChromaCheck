package br.com.java.tcc.application.services.util;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import br.com.java.tcc.application.services.resources.ServicesRequest;
import br.com.java.tcc.application.services.resources.ServicesResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicesMapper {

    public ServicesEntity toServices(ServicesRequest servicesDTO){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(servicesDTO.getCompanyEntity().getId());

        return ServicesEntity.builder()
                .companyEntity(companyEntity)
                .name(servicesDTO.getName())
                .build();
    }

    public ServicesResponse toServicesDTO(ServicesEntity servicesEntity){

        return new ServicesResponse(servicesEntity);
    }

    public List<ServicesResponse> toServicesDTO(List<ServicesEntity> servicesList){
        return servicesList.stream().map(ServicesResponse::new).collect(Collectors.toList());
    }

    public void updateServicesData(ServicesEntity servicesEntity, ServicesRequest servicesDTO){
        servicesEntity.setCompanyEntity(servicesDTO.getCompanyEntity());
        servicesEntity.setName(servicesDTO.getName());
    }
}
