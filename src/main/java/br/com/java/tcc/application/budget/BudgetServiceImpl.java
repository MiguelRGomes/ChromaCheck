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
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
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

    @Autowired
    MessageConfiguration messageConfiguration;

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
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.INVALID_ADDRESS_PERSON),
                    HttpStatus.BAD_REQUEST);
        }

        return budgetMapper.toBudgetDTO(budgetRepository.save(budgetEntity));
    }

    @Override
    public BudgetResponse update(Long id, BudgetRequest budgetDTO) {
        if (!budgetRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Orçamento)"), HttpStatus.NOT_FOUND);
        }

        BudgetEntity budgetEntity = returnBudget(id);
        budgetMapper.updateBudgetData(budgetEntity, budgetDTO);
        BudgetEntity updateEnity = budgetRepository.save(budgetEntity);
        return budgetMapper.toBudgetDTO(updateEnity);
    }

    @Override
    public String delete(Long id) {
        if (!budgetRepository.existsById(id)){
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Orçamento)"), HttpStatus.NOT_FOUND);
        }
        budgetRepository.deleteById(id);
        return "Orçamento id: " + id + " deletado";
    }

    public BudgetEntity returnBudget(Long id){
        return budgetRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Orçamento)"), HttpStatus.NOT_FOUND));
    }
}
