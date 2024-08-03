package br.com.java.tcc.application.budget.persistence;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "budget")
@NoArgsConstructor
@Getter
@Setter
public class BudgetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adress_id")
    private AdressEntity adressEntity;

    @Column(name = "creation_date", nullable = false)
    private Date creation_date;

    @Column(name = "expiration_date", nullable = false)
    private Date expiration_date;

    @Column(name = "approval", nullable = false)
    private Boolean approval;

    @Column(name = "total", nullable = false)
    private Float total;

    @Builder
    public BudgetEntity(CompanyEntity companyEntity, PersonEntity personEntity, AdressEntity adressEntity, Date creation_date, Date expiration_date, Boolean approval, Float total){
        this.companyEntity = companyEntity;
        this.personEntity = personEntity;
        this.adressEntity = adressEntity;
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
        this.approval = approval;
        this.total = total;
    }

    public void setId(Long id){
        this.id = id;
    }
}
