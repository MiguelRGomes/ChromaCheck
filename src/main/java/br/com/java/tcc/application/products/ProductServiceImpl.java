package br.com.java.tcc.application.products;

import br.com.java.tcc.application.company.CompanyService;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import br.com.java.tcc.application.products.persistence.ProductRepository;
import br.com.java.tcc.application.products.resources.ProductRequest;
import br.com.java.tcc.application.products.resources.ProductResponse;
import br.com.java.tcc.application.products.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CompanyService companyService;

    @Override
    public ProductResponse findById(Long id) {
        return productMapper.toProductDTO(returnProducts(id));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productMapper.toProductDTO(productRepository.findAll());
    }

    @Override
    public ProductResponse register(ProductRequest productDTO){

        ProductEntity productEntity = productMapper.toProduct(productDTO);

        CompanyEntity companyEntity = companyService.returnCompany(productDTO.getCompanyEntity().getId());
        productEntity.setCompanyEntity(companyEntity);

        return productMapper.toProductDTO(productRepository.save(productEntity));
    }

    @Override
    public ProductResponse update (Long id, ProductRequest productDTO){

        ProductEntity productEntity = returnProducts(id);
        productMapper.updateProductData(productEntity, productDTO);
        return productMapper.toProductDTO(productRepository.save(productEntity));
    }
    @Override
    public String delete(Long id){
        productRepository.deleteById(id);
        return "Product id: " + id + " deleted";
    }

    public ProductEntity returnProducts(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product wasn't found on database"));
    }
}
