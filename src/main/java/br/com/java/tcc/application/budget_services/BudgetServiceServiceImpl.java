package br.com.java.tcc.application.budget_services;

import br.com.java.tcc.application.budget_services.persistence.BudgetServiceEntity;
import br.com.java.tcc.application.budget_services.persistence.BudgetServiceRepository;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceRequest;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceResponse;
import br.com.java.tcc.application.budget_services.util.BudgetServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class BudgetServiceServiceImpl implements BudgetServiceService {

    private final BudgetServiceRepository budgetServiceRepository;
    private final BudgetServiceMapper budgetServiceMapper;

    @Override
    public BudgetServiceResponse findById(Long id) {

        return budgetServiceMapper.toBudgetServiceDTO(returnBudgetService(id));
    }

    @Override
    public List<BudgetServiceResponse> findAll() {
        return budgetServiceMapper.toBudgetServiceDTO(budgetServiceRepository.findAll());
    }

    @Override
    public BudgetServiceResponse register(BudgetServiceRequest budgetServiceDTO) {
        BudgetServiceEntity budgetServiceEntity = budgetServiceMapper.toBudgetService(budgetServiceDTO);
        return budgetServiceMapper.toBudgetServiceDTO(budgetServiceRepository.save(budgetServiceEntity));
    }

    @Override
    public BudgetServiceResponse update(Long id, BudgetServiceRequest budgetServiceDTO) {
        BudgetServiceEntity budgetServiceEntity = returnBudgetService(id);
        budgetServiceMapper.updateBudgetServiceData(budgetServiceEntity, budgetServiceDTO);
        return budgetServiceMapper.toBudgetServiceDTO (budgetServiceRepository.save(budgetServiceEntity));
    }

    @Override
    public String delete(Long id) {
        budgetServiceRepository .deleteById(id);
        return "Budget Service id: " + id + " deleted";
    }

    private BudgetServiceEntity returnBudgetService(Long id){
        return budgetServiceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Budget Service wasn't found on database"));
    }
}
