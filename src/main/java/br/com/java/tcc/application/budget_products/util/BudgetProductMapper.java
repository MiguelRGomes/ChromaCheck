package br.com.java.tcc.application.budget_products.util;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget_products.persistence.BudgetProductEntity;
import br.com.java.tcc.application.budget_products.resources.BudgetProductRequest;
import br.com.java.tcc.application.budget_products.resources.BudgetProductResponse;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BudgetProductMapper {

    public BudgetProductEntity toBudgetProduct(BudgetProductRequest budgetProductDTO){
        BudgetEntity budgetEntity = new BudgetEntity();
        budgetEntity.setId(budgetProductDTO.getBudgetEntity().getId());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(budgetProductDTO.getProductEntity().getId());

        return BudgetProductEntity.builder()
                .budgetEntity(budgetEntity)
                .productEntity(productEntity)
                .quantity(budgetProductDTO.getQuantity())
                .unit_price(budgetProductDTO.getUnit_price())
                .total(budgetProductDTO.getTotal())
                .approval(Boolean.parseBoolean(budgetProductDTO.getApproval()))
                .build();
    }

    public BudgetProductResponse toBudgetProductDTO(BudgetProductEntity budgetProductEntity){
        return new BudgetProductResponse(budgetProductEntity);
    }

    public List<BudgetProductResponse> toBudgetProductDTO(List<BudgetProductEntity> budgetProductList){
        return budgetProductList.stream().map(BudgetProductResponse::new).collect(Collectors.toList());
    }

    public void updateBudgetProductData(BudgetProductEntity budgetProductEntity, BudgetProductRequest budgetProductDTO){
        if (budgetProductDTO.getBudgetEntity() != null) {
            budgetProductEntity.setBudgetEntity(budgetProductDTO.getBudgetEntity());
        }
        if (budgetProductDTO.getProductEntity() != null) {
            budgetProductEntity.setProductEntity(budgetProductDTO.getProductEntity());
        }
        if (budgetProductDTO.getQuantity() != null) {
            budgetProductEntity.setQuantity(budgetProductDTO.getQuantity());
        }
        if (budgetProductDTO.getUnit_price() != null) {
            budgetProductEntity.setUnit_price(budgetProductDTO.getUnit_price());
        }
        if (budgetProductDTO.getTotal() != null) {
            budgetProductEntity.setTotal(budgetProductDTO.getTotal());
        }
        if (budgetProductDTO.getApproval() != null) {
            budgetProductEntity.setApproval(Boolean.parseBoolean(budgetProductDTO.getApproval()));
        }
    }
}
