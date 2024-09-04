package br.com.java.tcc.application.products;

import br.com.java.tcc.application.products.persistence.ProductEntity;
import br.com.java.tcc.application.products.resources.ProductRequest;
import br.com.java.tcc.application.products.resources.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse findById(Long id);

    ProductEntity returnProducts(Long id);

    ProductResponse register(ProductRequest productDTO);

    ProductResponse update(Long id, ProductRequest productDTO);

    String delete(Long id);

    List<ProductResponse> findByCompanyEntity(Long companyId);
}
