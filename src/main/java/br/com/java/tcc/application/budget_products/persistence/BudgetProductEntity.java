package br.com.java.tcc.application.budget_products.persistence;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "budget_product")
@NoArgsConstructor
@Getter
@Setter
public class BudgetProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private BudgetEntity budgetEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @Builder
    public BudgetProductEntity(Float quantity){
        this.quantity = quantity;
    }
}
