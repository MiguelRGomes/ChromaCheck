package br.com.java.tcc.application.products;

import br.com.java.tcc.application.company.CompanyService;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import br.com.java.tcc.application.products.persistence.ProductRepository;
import br.com.java.tcc.application.products.resources.ProductRequest;
import br.com.java.tcc.application.products.resources.ProductResponse;
import br.com.java.tcc.application.products.util.ProductMapper;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CompanyService companyService;

    @Autowired
    MessageConfiguration messageConfiguration;

    @Override
    public ProductResponse findById(Long id) {

        return productMapper.toProductDTO(returnProducts(id));
    }

    @Override
    public List<ProductResponse> findByCompanyEntity(Long companyId){
        CompanyEntity companyEntity = companyService.returnCompany(companyId);
        List<ProductEntity> products = productRepository.findByCompanyEntity(companyEntity);

        return productMapper.toProductDTO(products);
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
        if (!productRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Produto)"), HttpStatus.NOT_FOUND);
        }

        ProductEntity productEntity = returnProducts(id);
        productMapper.updateProductData(productEntity, productDTO);
        ProductEntity updateEntity = productRepository.save(productEntity);
        return productMapper.toProductDTO(updateEntity);
    }
    @Override
    public String delete(Long id){
        if (!productRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Produto)"), HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return "Produto id: " + id + " deletado";
    }

    public ProductEntity returnProducts(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Produto)"), HttpStatus.NOT_FOUND));
    }
}
