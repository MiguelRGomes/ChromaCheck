package br.com.java.tcc.application.budget_products;

import br.com.java.tcc.application.budget.BudgetService;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget_products.persistence.BudgetProductEntity;
import br.com.java.tcc.application.budget_products.persistence.BudgetProductRepository;
import br.com.java.tcc.application.budget_products.resources.BudgetProductRequest;
import br.com.java.tcc.application.budget_products.resources.BudgetProductResponse;
import br.com.java.tcc.application.budget_products.util.BudgetProductMapper;
import br.com.java.tcc.application.products.ProductService;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class BudgetProductServiceImpl implements BudgetProductService {

    @Autowired
    MessageConfiguration messageConfiguration;

    private final BudgetProductRepository budgetProductRepository;
    private final BudgetProductMapper budgetProductMapper;
    private final BudgetService budgetService;
    private final ProductService productService;

    @Override
    public BudgetProductResponse findById(Long id) {

        return budgetProductMapper.toBudgetProductDTO(returnBudgetProduct(id));
    }

    @Override
    public List<BudgetProductResponse> findAll() {
        return budgetProductMapper.toBudgetProductDTO(budgetProductRepository.findAll());
    }

    @Override
    public BudgetProductResponse register(BudgetProductRequest budgetProductDTO) {
        BudgetProductEntity budgetProductEntity = budgetProductMapper.toBudgetProduct(budgetProductDTO);

        BudgetEntity budgetEntity = budgetService.returnBudget(budgetProductDTO.getBudgetEntity().getId());
        budgetProductEntity.setBudgetEntity(budgetEntity);

        ProductEntity productEntity = productService.returnProducts(budgetProductDTO.getProductEntity().getId());
        budgetProductEntity.setProductEntity(productEntity);

        return budgetProductMapper.toBudgetProductDTO(budgetProductRepository.save(budgetProductEntity));
    }

    @Override
    public BudgetProductResponse update(Long id, BudgetProductRequest budgetProductDTO) {
        if (!budgetProductRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Produto do Orçamento)"), HttpStatus.NOT_FOUND);
        }

        BudgetProductEntity budgetProductEntity = returnBudgetProduct(id);
        budgetProductMapper.updateBudgetProductData(budgetProductEntity, budgetProductDTO);
        BudgetProductEntity updatedEntity = budgetProductRepository.save(budgetProductEntity);
        return budgetProductMapper.toBudgetProductDTO(updatedEntity);
    }

    @Override
    public String delete(Long id) {
        if (!budgetProductRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Produto do Orçamento)"), HttpStatus.NOT_FOUND);
        }
        budgetProductRepository .deleteById(id);
        return "Produto do Orçamento: " + id + " deletado";
    }

    public BudgetProductEntity returnBudgetProduct(Long id){
        return budgetProductRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Produto do Orçamento)"), HttpStatus.NOT_FOUND));
    }
}
