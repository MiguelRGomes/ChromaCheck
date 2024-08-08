package br.com.java.tcc.application.adresses.builders;

import br.com.java.tcc.application.adresses.persistence.AdressEntity;

public class AdressEntityBuilder {

    private AdressEntity adressEntity;

    private AdressEntityBuilder() {
    }

    public static AdressEntityBuilder getInstance(final Long id) {
        AdressEntityBuilder builder = new AdressEntityBuilder();
        builder.adressEntity = AdressEntity.builder()
                .personEntity(null) // Configurando personEntity com valor padrão
                .adress("Rua Exemplo")
                .number(123)
                .district("Centro")
                .cep(12345678)
                .city("São Paulo")
                .uf("SP")
                .build();
        return builder;
    }

    public AdressEntity getAdressEntity() {
        return this.adressEntity;
    }
}