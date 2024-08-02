package br.com.java.tcc.application.budget.util;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget.resources.BudgetRequest;
import br.com.java.tcc.application.budget.resources.BudgetResponse;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BudgetMapper {

    public BudgetEntity toBudget(BudgetRequest budgetDTO){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(budgetDTO.getCompanyEntity().getId());

        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(budgetDTO.getPersonEntity().getId());

        AdressEntity adressEntity = new AdressEntity();
        adressEntity.setId(budgetDTO.getAdressEntity().getId());

        return BudgetEntity.builder()
                .companyEntity(companyEntity)
                .personEntity(personEntity)
                .adressEntity(adressEntity)
                .creation_date(budgetDTO.getCreation_date())
                .expiration_date(budgetDTO.getExpiration_date())
                .approval(Boolean.parseBoolean(budgetDTO.getApproval()))
                .total(budgetDTO.getTotal())
                .build();
    }

    public BudgetResponse toBudgetDTO(BudgetEntity budgetEntity){
        return new BudgetResponse(budgetEntity);
    }

    public List<BudgetResponse> toBudgetDTO(List<BudgetEntity> budgetList){
        return budgetList.stream().map(BudgetResponse::new).collect(Collectors.toList());
    }

    public void updateBudgetData(BudgetEntity budgetEntity, BudgetRequest budgetDTO){
        if (budgetDTO.getCompanyEntity() != null) {
            budgetEntity.setCompanyEntity(budgetDTO.getCompanyEntity());
        }
        if (budgetDTO.getPersonEntity() != null) {
            budgetEntity.setPersonEntity(budgetDTO.getPersonEntity());
        }
        if (budgetDTO.getAdressEntity() != null) {
            budgetEntity.setAdressEntity(budgetDTO.getAdressEntity());
        }
        if (budgetDTO.getCreation_date() != null) {
            budgetEntity.setCreation_date(budgetDTO.getCreation_date());
        }
        if (budgetDTO.getExpiration_date() != null) {
            budgetEntity.setExpiration_date(budgetDTO.getExpiration_date());
        }
        if (budgetDTO.getApproval() != null) {
            budgetEntity.setApproval(Boolean.parseBoolean(budgetDTO.getApproval()));
        }
        if (budgetDTO.getTotal() != null) {
            budgetEntity.setTotal(budgetDTO.getTotal());
        }
    }
}
