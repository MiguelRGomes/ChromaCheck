package br.com.java.tcc.application.budget;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.persistence.AdressRepository;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget.persistence.BudgetRepository;
import br.com.java.tcc.application.budget.resources.BudgetRequest;
import br.com.java.tcc.application.budget.resources.BudgetResponse;
import br.com.java.tcc.application.budget.util.BudgetMapper;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.people.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;
    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;
    private final AdressRepository adressRepository;

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

        CompanyEntity companyEntity = companyRepository.findById(budgetDTO.getCompanyEntity().getId())
                .orElseThrow(() -> new RuntimeException("Company with id " + budgetDTO.getCompanyEntity().getId() + " not found"));

        // Verifica se a PersonEntity existe
        PersonEntity personEntity = personRepository.findById(budgetDTO.getPersonEntity().getId())
                .orElseThrow(() -> new RuntimeException("Person with id " + budgetDTO.getPersonEntity().getId() + " not found"));

        // Verifica se a AdressEntity existe
        AdressEntity adressEntity = adressRepository.findById(budgetDTO.getAdressEntity().getId())
                .orElseThrow(() -> new RuntimeException("Address with id " + budgetDTO.getAdressEntity().getId() + " not found"));

        // Verifica se a adressEntity pertence à personEntity
        if (!adressEntity.getPersonEntity().getId().equals(personEntity.getId())) {
            throw new RuntimeException("Address with id " + adressEntity.getId() + " does not belong to Person with id " + personEntity.getId());
        }

        budgetEntity.setCompanyEntity(companyEntity);
        budgetEntity.setPersonEntity(personEntity);
        budgetEntity.setAdressEntity(adressEntity);

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
