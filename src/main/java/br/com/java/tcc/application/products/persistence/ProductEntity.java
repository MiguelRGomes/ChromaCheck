package br.com.java.tcc.application.products.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Builder
    private ProductEntity(CompanyEntity companyEntity, String name, String description){
        this.companyEntity =  companyEntity;
        this.name = name;
        this.description = description;
    }
    public void setId(Long id){
        this.Id = id;
    }
}
