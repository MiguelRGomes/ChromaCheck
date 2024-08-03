package br.com.java.tcc.application.people.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "people")
@NoArgsConstructor
@Getter
@Setter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity companyEntity;

    @Column(name = "type", nullable = false)
    private String type;

    @NotBlank(message = "O Nome é obrigatório e não pode estar vazio.")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpf_cnpj;

    @NotBlank(message = "O telefon é obrigatório e não pode estar vazio.")
    @Column(name = "fone", nullable = false)
    private String fone;

    @NotBlank(message = "O email é obrigatório e não pode estar vazio.")
    @Column(name = "email", nullable = false)
    private String email;

    @Builder
    public PersonEntity(CompanyEntity companyEntity, String type, String name, String cpf_cnpj, String fone, String email) {
        this.companyEntity = companyEntity;
        this.type = type;
        this.name = name;
        this.cpf_cnpj = cpf_cnpj;
        this.fone = fone;
        this.email = email;
    }

    public void setId(Long id){
        this.id = id;
    }

}