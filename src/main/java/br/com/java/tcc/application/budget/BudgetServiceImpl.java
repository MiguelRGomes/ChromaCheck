package br.com.java.tcc.application.budget;

import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget.persistence.BudgetRepository;
import br.com.java.tcc.application.budget.resources.BudgetRequest;
import br.com.java.tcc.application.budget.resources.BudgetResponse;
import br.com.java.tcc.application.budget.util.BudgetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;

    @Override
    public BudgetResponse findById(Long id) {

        return budgetMapper.toBudgetDTO(returnBudget(id));
    }

    @Override
    public List<BudgetResponse> findAll() {
        return budgetMapper.toBudgetDTO(budgetRepository.findAll());
    }

    @Override
    public BudgetResponse register(BudgetRequest budgetDTO) {
        BudgetEntity budgetEntity = budgetMapper.toBudget(budgetDTO);
        return budgetMapper.toBudgetDTO(budgetRepository.save(budgetEntity));
    }

    @Override
    public BudgetResponse update(Long id, BudgetRequest budgetDTO) {
        BudgetEntity budgetEntity = returnBudget(id);
        budgetMapper.updateBudgetData(budgetEntity, budgetDTO);
        return budgetMapper.toBudgetDTO (budgetRepository.save(budgetEntity));
    }

    @Override
    public String delete(Long id) {
        budgetRepository .deleteById(id);
        return "Budget id: " + id + " deleted";
    }

    private BudgetEntity returnBudget(Long id){
        return budgetRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Budget wasn't found on database"));
    }
}
