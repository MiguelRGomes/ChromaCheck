package br.com.java.tcc.application.budget_services.persistence;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "budget_service")
@NoArgsConstructor
@Getter
@Setter
public class BudgetServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private BudgetEntity budgetEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    private ServicesEntity servicesEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prices_id")
    private PricesEntity pricesEntity;

    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @Column(name = "discount", nullable = false)
    private Float discount;

    @Builder
    public BudgetServiceEntity(Float quantity, Float discount){
        this.quantity = quantity;
        this.discount = discount;
    }
}
