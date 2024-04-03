package br.com.java.tcc.application.services.persistence;

import jakarta.persistence.*;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public ServicesEntity(String name){

        this.name = name;
    }
}
