package br.com.java.tcc.application.products.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByCompanyEntity(CompanyEntity companyEntity);
}
