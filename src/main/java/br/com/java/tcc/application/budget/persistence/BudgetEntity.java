package br.com.java.tcc.application.budget.persistence;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adress_id")
    private AdressEntity adressEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @Column(name = "creation_date", nullable = false)
    private Date creation_date;

    @Column(name = "expiration_date", nullable = false)
    private Date expiration_date;

    @Column(name = "approval", nullable = false)
    private Boolean approval;

    @Column(name = "total", nullable = false)
    private Float total;

    @Builder
    public BudgetEntity(Date creation_date, Date expiration_date, Boolean approval, Float total){
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
        this.approval = approval;
        this.total = total;
    }
}
