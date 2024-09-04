package br.com.java.tcc.application.prices.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

    List<PricesEntity> findByCompanyEntity(CompanyEntity companyEntity);
}
