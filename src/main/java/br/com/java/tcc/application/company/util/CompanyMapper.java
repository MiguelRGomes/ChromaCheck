package br.com.java.tcc.application.company.util;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.resources.CompanyRequest;
import br.com.java.tcc.application.company.resources.CompanyResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    public CompanyEntity toCompany(CompanyRequest companyDTO){

        return CompanyEntity.builder()
                .cnpj(companyDTO.getCnpj())
                .name(companyDTO.getName())
                .address(companyDTO.getAddress())
                .number(companyDTO.getNumber())
                .district(companyDTO.getDistrict())
                .cep(companyDTO.getCep())
                .city(companyDTO.getCity())
                .uf(companyDTO.getUf())
                .fone(companyDTO.getFone())
                .email(companyDTO.getEmail())
                .build();
    }

    public CompanyResponse toCompanyDTO(CompanyEntity companyEntity){

        return new CompanyResponse(companyEntity);
    }

    public List<CompanyResponse> toCompanyDTO(List<CompanyEntity> companyList){
        return companyList.stream().map(CompanyResponse::new).collect(Collectors.toList());
    }

    public void updateCompanyData(CompanyEntity companyEntity, CompanyRequest companyDTO){
        companyEntity.setCnpj(companyDTO.getCnpj());
        companyEntity.setName(companyDTO.getName());
        companyEntity.setAddress(companyDTO.getAddress());
        companyEntity.setNumber(companyDTO.getNumber());
        companyEntity.setDistrict(companyDTO.getDistrict());
        companyEntity.setCep(companyDTO.getCep());
        companyEntity.setCity(companyDTO.getCity());
        companyEntity.setUf(companyDTO.getUf());
        companyEntity.setFone(companyDTO.getFone());
        companyEntity.setEmail(companyDTO.getEmail());
    }
}
