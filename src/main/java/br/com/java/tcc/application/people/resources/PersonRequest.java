package br.com.java.tcc.application.people.resources;

import lombok.Getter;

@Getter
public class PersonRequest {

    private String type;

    private String name;

    private String cpf;

    private String fone;

    private String email;

}