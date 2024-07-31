package br.com.java.tcc.application.budget_services.util;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget_services.persistence.BudgetServiceEntity;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceRequest;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceResponse;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BudgetServiceMapper {

    public BudgetServiceEntity toBudgetService(BudgetServiceRequest budgetServiceDTO){
        BudgetEntity budgetEntity = new BudgetEntity();
        budgetEntity.setId(budgetServiceDTO.getBudgetEntity().getId());

        ServicesEntity servicesEntity = new ServicesEntity();
        servicesEntity.setId(budgetServiceDTO.getServicesEntity().getId());

        PricesEntity pricesEntity = new PricesEntity();
        pricesEntity.setId(budgetServiceDTO.getPricesEntity().getId());

        return BudgetServiceEntity.builder()
                .budgetEntity(budgetEntity)
                .servicesEntity(servicesEntity)
                .pricesEntity(pricesEntity)
                .quantity(budgetServiceDTO.getQuantity())
                .discount(budgetServiceDTO.getDiscount())
                .build();
    }

    public BudgetServiceResponse toBudgetServiceDTO(BudgetServiceEntity budgetServiceEntity){
        return new BudgetServiceResponse(budgetServiceEntity);
    }

    public List<BudgetServiceResponse> toBudgetServiceDTO(List<BudgetServiceEntity> budgetServiceList){
        return budgetServiceList.stream().map(BudgetServiceResponse::new).collect(Collectors.toList());
    }

    public void updateBudgetServiceData(BudgetServiceEntity budgetServiceEntity, BudgetServiceRequest budgetServiceDTO){
        budgetServiceEntity.setBudgetEntity(budgetServiceDTO.getBudgetEntity());
        budgetServiceEntity.setServicesEntity(budgetServiceDTO.getServicesEntity());
        budgetServiceEntity.setPricesEntity(budgetServiceDTO.getPricesEntity());
        budgetServiceEntity.setQuantity(budgetServiceDTO.getQuantity());
        budgetServiceEntity.setDiscount(budgetServiceDTO.getDiscount());
    }
}
