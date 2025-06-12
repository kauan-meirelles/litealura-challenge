package br.com.literalura.literalura.model;

import jakarta.persistence.*;

@Entity
public class Livro {
    @Id @GeneratedValue
    private Long id;

    private String titulo;
    private String idioma;
    private int downloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}
    public Livro(String titulo, String idioma, int downloads, Autor autor) {
        this.titulo = titulo; this.idioma = idioma;
        this.downloads = downloads; this.autor = autor;
    }

    @Override
    public String toString() {
        return titulo + " [" + idioma + "] – " + autor.getNome() + " (downloads: " + downloads + ")";
    }
}