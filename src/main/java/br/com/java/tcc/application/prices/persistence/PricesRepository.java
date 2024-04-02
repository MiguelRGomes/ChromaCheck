package br.com.java.tcc.application.prices.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

}
