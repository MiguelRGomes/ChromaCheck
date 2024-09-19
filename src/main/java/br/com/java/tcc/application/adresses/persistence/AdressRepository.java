package br.com.java.tcc.application.adresses.persistence;

import br.com.java.tcc.application.people.persistence.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressRepository extends JpaRepository<AdressEntity, Long> {

    List<AdressEntity> findByPersonEntity(PersonEntity personEntity);
}
