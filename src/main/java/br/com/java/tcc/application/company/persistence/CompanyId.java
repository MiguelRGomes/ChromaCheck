package br.com.java.tcc.application.company.persistence;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CompanyId implements Serializable {

    private Long id;
    private String cnpj;
}
