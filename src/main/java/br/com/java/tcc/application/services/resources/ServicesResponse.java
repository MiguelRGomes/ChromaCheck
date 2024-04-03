package br.com.java.tcc.application.services.resources;

import br.com.java.tcc.application.services.persistence.ServicesEntity;
import lombok.Getter;

@Getter
public class ServicesResponse {
    private Long id;

    private String name;

    public ServicesResponse(ServicesEntity servicesEntity){
        this.id = servicesEntity.getId();
        this.name = servicesEntity.getName();
    }
}
