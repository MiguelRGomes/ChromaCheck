package br.com.java.tcc.application.budget_products.persistence;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.budget.persistence.BudgetEntity;
import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import br.com.java.tcc.application.products.persistence.ProductEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private BudgetEntity budgetEntity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @Column(name = "unit_price", nullable = false)
    private Float unit_price;

    @Column(name = "total", nullable = false)
    private Float total;

    @Column(name = "approval", nullable = false)
    private Boolean approval;
    @Builder
    public BudgetProductEntity(BudgetEntity budgetEntity, ProductEntity productEntity, Float quantity, Float unit_price, Float total, Boolean approval){
        this.budgetEntity = budgetEntity;
        this.productEntity = productEntity;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.total = total;
        this.approval = approval;
    }
}
