package br.com.java.tcc.application.company.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@NoArgsConstructor
@Getter
@Setter
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long Id;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false)
    private String uf;

    @Column(name = "fone", nullable = false)
    private String fone;

    @Column(name = "email", nullable = false)
    private String email;

    @Builder
    public CompanyEntity(String cnpj, String name, String address, Integer number, String district, String cep, String city, String uf, String fone, String email) {
        this.cnpj = cnpj;
        this.name = name;
        this.address = address;
        this.number = number;
        this.district = district;
        this.cep = cep;
        this.city = city;
        this.uf = uf;
        this.fone = fone;
        this.email = email;
    }

    public void setId(Long id){
        this.Id = id;
    }
}