package br.com.java.tcc.application.budget_services.util;

import br.com.java.tcc.application.budget_services.persistence.BudgetServiceEntity;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceRequest;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BudgetServiceMapper {

    public BudgetServiceEntity toBudgetService(BudgetServiceRequest budgetServiceDTO){
        return BudgetServiceEntity.builder()
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
        budgetServiceEntity.setQuantity(budgetServiceDTO.getQuantity());
        budgetServiceEntity.setDiscount(budgetServiceDTO.getDiscount());
    }
}
