package br.com.java.tcc.application.budget_services;

import br.com.java.tcc.application.budget.BudgetService;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.budget_services.persistence.BudgetServiceEntity;
import br.com.java.tcc.application.budget_services.persistence.BudgetServiceRepository;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceRequest;
import br.com.java.tcc.application.budget_services.resources.BudgetServiceResponse;
import br.com.java.tcc.application.budget_services.util.BudgetServiceMapper;
import br.com.java.tcc.application.prices.PricesService;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.services.ServicesService;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class BudgetServiceServiceImpl implements BudgetServiceService {

    @Autowired
    MessageConfiguration messageConfiguration;

    private final BudgetServiceRepository budgetServiceRepository;
    private final BudgetServiceMapper budgetServiceMapper;
    private final BudgetService budgetService;
    private final ServicesService servicesService;
    private final PricesService pricesService;

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

        BudgetEntity budgetEntity = budgetService.returnBudget(budgetServiceDTO.getBudgetEntity().getId());
        budgetServiceEntity.setBudgetEntity(budgetEntity);

        ServicesEntity servicesEntity = servicesService.returnServices(budgetServiceDTO.getServicesEntity().getId());
        budgetServiceEntity.setServicesEntity(servicesEntity);

        PricesEntity pricesEntity = pricesService.returnPrices(budgetServiceDTO.getPricesEntity().getId());
        budgetServiceEntity.setPricesEntity(pricesEntity);

        return budgetServiceMapper.toBudgetServiceDTO(budgetServiceRepository.save(budgetServiceEntity));
    }

    @Override
    public BudgetServiceResponse update(Long id, BudgetServiceRequest budgetServiceDTO) {
        if (!budgetServiceRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Serviço do Orçamento)"), HttpStatus.NOT_FOUND);
        }

        BudgetServiceEntity budgetServiceEntity = returnBudgetService(id);
        budgetServiceMapper.updateBudgetServiceData(budgetServiceEntity, budgetServiceDTO);
        BudgetServiceEntity updatedEntity = budgetServiceRepository.save(budgetServiceEntity);
        return budgetServiceMapper.toBudgetServiceDTO(updatedEntity);
    }

    @Override
    public String delete(Long id) {
        if (!budgetServiceRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Serviço do Orçamento)"), HttpStatus.NOT_FOUND);
        }

        budgetServiceRepository .deleteById(id);
        return "Budget Service id: " + id + " deleted";
    }

    public BudgetServiceEntity returnBudgetService(Long id){
        return budgetServiceRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Serviço do Orçamento)"), HttpStatus.NOT_FOUND));
    }
}
