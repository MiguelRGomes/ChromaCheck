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
        if (companyDTO.getCnpj() != null) {
            companyEntity.setCnpj(companyDTO.getCnpj());
        }
        if (companyDTO.getName() != null) {
            companyEntity.setName(companyDTO.getName());
        }
        if (companyDTO.getAddress() != null) {
            companyEntity.setAddress(companyDTO.getAddress());
        }
        if (companyDTO.getNumber() != null) {
            companyEntity.setNumber(companyDTO.getNumber());
        }
        if (companyDTO.getDistrict() != null) {
            companyEntity.setDistrict(companyDTO.getDistrict());
        }
        if (companyDTO.getCep() != null) {
            companyEntity.setCep(companyDTO.getCep());
        }
        if (companyDTO.getCity() != null) {
            companyEntity.setCity(companyDTO.getCity());
        }
        if (companyDTO.getUf() != null) {
            companyEntity.setUf(companyDTO.getUf());
        }
        if (companyDTO.getFone() != null) {
            companyEntity.setFone(companyDTO.getFone());
        }
        if (companyDTO.getEmail() != null) {
            companyEntity.setEmail(companyDTO.getEmail());
        }
    }
}
