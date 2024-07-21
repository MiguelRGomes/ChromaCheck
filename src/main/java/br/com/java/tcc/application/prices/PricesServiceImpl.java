package br.com.java.tcc.application.prices;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.company.persistence.CompanyRepository;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.prices.persistence.PricesRepository;
import br.com.java.tcc.application.prices.resources.PricesController;
import br.com.java.tcc.application.prices.resources.PricesRequest;
import br.com.java.tcc.application.prices.resources.PricesResponse;
import br.com.java.tcc.application.prices.util.PricesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class PricesServiceImpl implements PricesService {

    private final PricesRepository pricesRepository;

    private final PricesMapper pricesMapper;

    private final CompanyRepository companyRepository;

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

        Optional<CompanyEntity> optional =  companyRepository.findById(pricesDTO.getCompanyEntity().getId());
        if (optional.isPresent()){
            CompanyEntity companyEntity =  optional.get();
            pricesEntity.setCompanyEntity(companyEntity);
            return pricesMapper.toPricesDTO(pricesRepository.save(pricesEntity));
        }
        else {
            throw new RuntimeException("Company with id " + pricesDTO.getCompanyEntity().getId() + " not found");
        }
    }

    @Override
    public PricesResponse update(Long id, PricesRequest pricesDTO){
        PricesEntity pricesEntity = returnPrices(id);
        pricesMapper.updatePricesData(pricesEntity, pricesDTO);
        return pricesMapper.toPricesDTO(pricesRepository.save(pricesEntity));
    }

    @Override
    public String delete(Long id) {
        pricesRepository.deleteById(id);
        return "Prices id: " + id + " deleted";
    }
    private PricesEntity returnPrices(Long id) {
        return pricesRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Prices wasn't found on database"));
    }
}
