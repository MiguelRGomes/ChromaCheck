package br.com.java.tcc.application.people.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "fone", nullable = false)
    private String fone;

    @Column(name = "email", nullable = false)
    private String email;

    @Builder
    public PersonEntity(String type, String name, String cpf, String fone, String email) {
        this.type = type;
        this.name = name;
        this.cpf = cpf;
        this.fone = fone;
        this.email = email;
    }

}