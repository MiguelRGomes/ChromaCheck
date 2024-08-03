package br.com.java.tcc.application.prices.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "prices")
@NoArgsConstructor
@Getter
@Setter
public class PricesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long Id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity companyEntity;

    @NotBlank(message = "O nome é obrigatório e não pode estar vazio.")
    @Column(name = "name", nullable = false)
    private String name;

    @Positive(message = "A medida deve ser maior que 0.")
    @Column(name = "square_meter", nullable = true)
    private Float square_meter;

    @Positive(message = "O preço deve ser maior que 0.")
    @Column(name = "fixed_price", nullable = true)
    private Float fixed_price;

    @Builder
    public  PricesEntity(CompanyEntity companyEntity, String name, Float square_meter, Float fixed_price){
        this.companyEntity = companyEntity;
        this.name = name;
        this.square_meter = square_meter;
        this.fixed_price = fixed_price;
    }

    public void setId(Long id){
        this.Id = id;
    }
}
