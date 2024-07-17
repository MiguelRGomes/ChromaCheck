package br.com.java.tcc.application.budget_products;

import br.com.java.tcc.application.budget_products.persistence.BudgetProductEntity;
import br.com.java.tcc.application.budget_products.persistence.BudgetProductRepository;
import br.com.java.tcc.application.budget_products.resources.BudgetProductRequest;
import br.com.java.tcc.application.budget_products.resources.BudgetProductResponse;
import br.com.java.tcc.application.budget_products.util.BudgetProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class BudgetProductServiceImpl implements BudgetProductService {

    private final BudgetProductRepository budgetProductRepository;
    private final BudgetProductMapper budgetProductMapper;


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
        return budgetProductMapper.toBudgetProductDTO(budgetProductRepository.save(budgetProductEntity));
    }

    @Override
    public BudgetProductResponse update(Long id, BudgetProductRequest budgetProductDTO) {
        BudgetProductEntity budgetProductEntity = returnBudgetProduct(id);
        budgetProductMapper.updateBudgetProductData(budgetProductEntity, budgetProductDTO);
        return budgetProductMapper.toBudgetProductDTO (budgetProductRepository.save(budgetProductEntity));
    }

    @Override
    public String delete(Long id) {
        budgetProductRepository .deleteById(id);
        return "Budget Product id: " + id + " deleted";
    }

    private BudgetProductEntity returnBudgetProduct(Long id){
        return budgetProductRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Budget Product wasn't found on database"));
    }
}
