package br.com.java.tcc.application.products.util;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import br.com.java.tcc.application.products.resources.ProductRequest;
import br.com.java.tcc.application.products.resources.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductEntity toProduct(ProductRequest productDTO){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(productDTO.getCompanyEntity().getId());

        return ProductEntity.builder()
                .companyEntity(companyEntity)
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .build();
    }

    public ProductResponse toProductDTO(ProductEntity productEntity){

        return new ProductResponse(productEntity);
    }

    public List<ProductResponse> toProductDTO(List<ProductEntity> productList){
        return productList.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    public void updateProductData(ProductEntity productEntity, ProductRequest productDTO){
        if (productDTO.getCompanyEntity() != null) {
            productEntity.setCompanyEntity(productDTO.getCompanyEntity());
        }
        if (productDTO.getName() != null) {
            productEntity.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            productEntity.setDescription(productDTO.getDescription());
        }
    }
}
