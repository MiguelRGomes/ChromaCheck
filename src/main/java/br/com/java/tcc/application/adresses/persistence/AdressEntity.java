package br.com.java.tcc.application.adresses.persistence;

import br.com.java.tcc.application.company.persistence.CompanyEntity;
import br.com.java.tcc.application.people.persistence.PersonEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresses")
@NoArgsConstructor
@Getter
@Setter
public class AdressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @Column(name = "adress", nullable = false)
    private String adress;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "cep", nullable = false)
    private Integer cep;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false)
    private String uf;
    @Builder
    public AdressEntity(String adress, Integer number, String district, Integer cep, String city, String uf){
        this.adress = adress;
        this.number = number;
        this.district = district;
        this.cep = cep;
        this.city = city;
        this.uf = uf;
    }
}
