package br.com.java.tcc.application.adresses.persistence;

import br.com.java.tcc.application.people.persistence.PersonEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "adress")
@NoArgsConstructor
@Getter
@Setter
public class AdressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @NotBlank(message = "O endereço é obrigatório e não pode estar vazio.")
    @Column(name = "adress", nullable = false)
    private String adress;

    @Positive(message = "O número deve ser maior que 0.")
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotBlank(message = "O bairro é obrigatório e não pode estar vazio.")
    @Column(name = "district", nullable = false)
    private String district;

    @Positive(message = "O cep deve ser maior que 0.")
    @Column(name = "cep", nullable = false)
    private Integer cep;

    @NotBlank(message = "A cidade é obrigatória e não pode estar vazia.")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "A uf é obrigatória e não pode estar vazia.")
    @Column(name = "uf", nullable = false)
    private String uf;
    @Builder
    public AdressEntity(PersonEntity personEntity, String adress, Integer number, String district, Integer cep, String city, String uf){
        this.personEntity = personEntity;
        this.adress = adress;
        this.number = number;
        this.district = district;
        this.cep = cep;
        this.city = city;
        this.uf = uf;
    }

    public void setId(Long id){
        this.id = id;
    }
}
