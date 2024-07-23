package br.com.java.tcc.application.products.resources;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private CompanyEntity companyEntity;
    private String name;
    private String description;

    public ProductResponse(ProductEntity productEntity){
        this.id = productEntity.getId();
        this.companyEntity = productEntity.getCompanyEntity();
        this.name = productEntity.getName();
        this.description = productEntity.getDescription();
    }
}
