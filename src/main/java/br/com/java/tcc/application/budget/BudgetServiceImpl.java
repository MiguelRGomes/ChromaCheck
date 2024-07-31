package br.com.java.tcc.application.budget;

import br.com.java.tcc.application.adresses.AdressService;
import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.persistence.AdressRepository;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget.persistence.BudgetRepository;
import br.com.java.tcc.application.budget.resources.BudgetRequest;
import br.com.java.tcc.application.budget.resources.BudgetResponse;
import br.com.java.tcc.application.budget.util.BudgetMapper;
import br.com.java.tcc.application.company.CompanyService;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.people.PersonService;
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
    private final CompanyService companyService;
    private final PersonService personService;
    private final AdressService adressService;

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

        CompanyEntity companyEntity = companyService.returnCompany(budgetDTO.getCompanyEntity().getId());
        budgetEntity.setCompanyEntity(companyEntity);

        PersonEntity personEntity = personService.returnPerson(budgetDTO.getPersonEntity().getId());
        budgetEntity.setPersonEntity(personEntity);

        AdressEntity adressEntity = adressService.returnAdress(budgetDTO.getAdressEntity().getId());
        budgetEntity.setAdressEntity(adressEntity);

        // Verifica se a adressEntity pertence à personEntity
        if (!adressEntity.getPersonEntity().getId().equals(personEntity.getId())) {
            throw new RuntimeException("Address with id " + adressEntity.getId() + " does not belong to Person with id " + personEntity.getId());
        }

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

    public BudgetEntity returnBudget(Long id){
        return budgetRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Budget wasn't found on database"));
    }
}
