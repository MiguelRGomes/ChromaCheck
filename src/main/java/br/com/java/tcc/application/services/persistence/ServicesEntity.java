package br.com.java.tcc.application.services.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "services")
@NoArgsConstructor
@Getter
@Setter
public class ServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long Id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @NotBlank(message = "O nome é obrigatório e não pode estar vazio.")
    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public ServicesEntity(CompanyEntity companyEntity, String name){
        this.companyEntity = companyEntity;
        this.name = name;
    }

    public void setId(Long Id){
        this.Id = Id;
    }
}
