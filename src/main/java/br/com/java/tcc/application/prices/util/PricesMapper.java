package br.com.java.tcc.application.prices.util;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.prices.persistence.PricesRepository;
import br.com.java.tcc.application.prices.resources.PricesRequest;
import br.com.java.tcc.application.prices.resources.PricesResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricesMapper {

    public PricesEntity toPrices(PricesRequest pricesDTO){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(pricesDTO.getCompanyEntity().getId());

        return PricesEntity.builder()
                .companyEntity(companyEntity)
                .name(pricesDTO.getName())
                .square_meter(pricesDTO.getSquare_meter())
                .fixed_price(pricesDTO.getFixed_price())
                .build();
    }

    public PricesResponse toPricesDTO(PricesEntity pricesEntity){
        return new PricesResponse(pricesEntity);
    }

    public List<PricesResponse> toPricesDTO(List<PricesEntity> pricesList){
        return pricesList.stream().map(PricesResponse::new).collect(Collectors.toList());
    }

    public void updatePricesData(PricesEntity pricesEntity, PricesRequest pricesDTO){
        if (pricesDTO.getCompanyEntity() != null) {
            pricesEntity.setCompanyEntity(pricesDTO.getCompanyEntity());
        }
        if (pricesDTO.getName() != null) {
            pricesEntity.setName(pricesDTO.getName());
        }
        if (pricesDTO.getSquare_meter() != null) {
            pricesEntity.setSquare_meter(pricesDTO.getSquare_meter());
        }
        if (pricesDTO.getFixed_price() != null) {
            pricesEntity.setFixed_price(pricesDTO.getFixed_price());
        }
    }
}
