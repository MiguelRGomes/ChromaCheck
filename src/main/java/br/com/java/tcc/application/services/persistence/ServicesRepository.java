package br.com.java.tcc.application.services.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {
    List<ServicesEntity> findByCompanyEntity(CompanyEntity companyEntity);
}
