package br.com.java.tcc.application.budget_services.persistence;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.prices.persistence.PricesEntity;
import br.com.java.tcc.application.services.persistence.ServicesEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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
    @JoinColumn(name = "service_id")
    private ServicesEntity servicesEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prices_id")
    private PricesEntity pricesEntity;

    @Positive(message = "A quantidade deve ser maior que 0.")
    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @Column(name = "discount", nullable = false)
    private Float discount;

    @Builder
    public BudgetServiceEntity(BudgetEntity budgetEntity, ServicesEntity servicesEntity, PricesEntity pricesEntity, Float quantity, Float discount){
        this.budgetEntity = budgetEntity;
        this.servicesEntity = servicesEntity;
        this.pricesEntity = pricesEntity;
        this.quantity = quantity;
        this.discount = discount;
    }
}
