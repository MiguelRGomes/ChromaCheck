package br.com.java.tcc.application.budget_products.util;

import br.com.java.tcc.application.budget_products.persistence.BudgetProductEntity;
import br.com.java.tcc.application.budget_products.resources.BudgetProductRequest;
import br.com.java.tcc.application.budget_products.resources.BudgetProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BudgetProductMapper {

    public BudgetProductEntity toBudgetProduct(BudgetProductRequest budgetProductDTO){
        return BudgetProductEntity.builder()
                .quantity((budgetProductDTO.getQuantity()))
                .build();
    }

    public BudgetProductResponse toBudgetProductDTO(BudgetProductEntity budgetProductEntity){
        return new BudgetProductResponse(budgetProductEntity);
    }

    public List<BudgetProductResponse> toBudgetProductDTO(List<BudgetProductEntity> budgetProductList){
        return budgetProductList.stream().map(BudgetProductResponse::new).collect(Collectors.toList());
    }

    public void updateBudgetProductData(BudgetProductEntity budgetProductEntity, BudgetProductRequest budgetProductDTO){
        budgetProductEntity.setBudgetEntity(budgetProductDTO.getBudgetEntity());
        budgetProductEntity.setProductEntity(budgetProductDTO.getProductEntity());
        budgetProductEntity.setQuantity(budgetProductDTO.getQuantity());
        budgetProductEntity.setUnit_price(budgetProductDTO.getUnit_price());
        budgetProductEntity.setTotal(budgetProductDTO.getTotal());
        budgetProductEntity.setApproval(budgetProductDTO.getApproval());
    }
}
