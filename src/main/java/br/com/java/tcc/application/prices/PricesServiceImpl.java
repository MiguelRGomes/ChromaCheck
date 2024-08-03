package br.com.java.tcc.application.prices;

import br.com.java.tcc.application.company.CompanyService;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.prices.persistence.PricesRepository;
import br.com.java.tcc.application.prices.resources.PricesController;
import br.com.java.tcc.application.prices.resources.PricesRequest;
import br.com.java.tcc.application.prices.resources.PricesResponse;
import br.com.java.tcc.application.prices.util.PricesMapper;
import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class PricesServiceImpl implements PricesService {

    private final PricesRepository pricesRepository;

    private final PricesMapper pricesMapper;

    private final CompanyService companyService;

    @Autowired
    MessageConfiguration messageConfiguration;

    @Override
    public PricesResponse findById(Long id){
        return pricesMapper.toPricesDTO(returnPrices(id));
    }

    @Override
    public List<PricesResponse> findAll(){
        return pricesMapper.toPricesDTO(pricesRepository.findAll());
    }

    @Override
    public PricesResponse register(PricesRequest pricesDTO) {
        PricesEntity pricesEntity = pricesMapper.toPrices(pricesDTO);

        CompanyEntity companyEntity = companyService.returnCompany(pricesDTO.getCompanyEntity().getId());
        pricesEntity.setCompanyEntity(companyEntity);

        return pricesMapper.toPricesDTO(pricesRepository.save(pricesEntity));
    }

    @Override
    public PricesResponse update(Long id, PricesRequest pricesDTO){
        if (!pricesRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Tabela de Preços)"), HttpStatus.NOT_FOUND);
        }

        PricesEntity pricesEntity = returnPrices(id);
        pricesMapper.updatePricesData(pricesEntity, pricesDTO);
        PricesEntity updateEntity = pricesRepository.save(pricesEntity);
        return pricesMapper.toPricesDTO(updateEntity);
    }

    @Override
    public String delete(Long id) {
        if (!pricesRepository.existsById(id)) {
            throw new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Tabela de Preços)"), HttpStatus.NOT_FOUND);
        }

        pricesRepository.deleteById(id);
        return "Preço id: " + id + " deletado";
    }
    public PricesEntity returnPrices(Long id) {
        return pricesRepository.findById(id)
                .orElseThrow(()-> new CustomException(messageConfiguration.getMessageByCode(MessageCodeEnum.REGISTER_NOT_FOUND, "(Tabela de Preços)"), HttpStatus.NOT_FOUND));
    }
}
