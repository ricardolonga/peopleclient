package br.com.ricardolonga.peopleclient.model;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private String nome;

    public Pessoa() {
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
