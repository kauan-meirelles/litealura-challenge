package br.com.literalura.literalura.model;

import jakarta.persistence.*;

@Entity
public class Autor {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private int anoNascimento;
    private int anoFalecimento;

    public Autor() {}

    public Autor(String nome, int nasc, int falec) {
        this.nome = nome;
        this.anoNascimento = nasc;
        this.anoFalecimento = falec;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public int getAnoFalecimento() {
        return anoFalecimento;
    }

    // Se quiser pode adicionar setters também

    @Override
    public String toString() {
        return nome + " (" + anoNascimento + "–" + anoFalecimento + ")";
    }
}