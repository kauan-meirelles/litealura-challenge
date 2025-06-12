package br.com.literalura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroDTO {

    @JsonAlias("title")
    private String title;

    @JsonAlias("languages")
    private String[] languages;

    @JsonAlias("download_count")
    private int downloads;

    @JsonAlias("authors")
    private AutorInfo[] authors;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AutorInfo {
        @JsonAlias("name")
        public String name;

        @JsonAlias("birth_year")
        public Integer birth_year;

        @JsonAlias("death_year")
        public Integer death_year;

        // Getters e Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getBirth_year() {
            return birth_year;
        }

        public void setBirth_year(Integer birth_year) {
            this.birth_year = birth_year;
        }

        public Integer getDeath_year() {
            return death_year;
        }

        public void setDeath_year(Integer death_year) {
            this.death_year = death_year;
        }
    }

    // Getters e Setters

    public String getTitle() {
        return title != null ? title : "Título desconhecido";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public AutorInfo[] getAuthors() {
        return authors;
    }

    public void setAuthors(AutorInfo[] authors) {
        this.authors = authors;
    }

    // Métodos utilitários para obter informações formatadas

    public String getIdioma() {
        return (languages != null && languages.length > 0) ? languages[0] : "Idioma desconhecido";
    }

    public String getAutorNome() {
        if (authors != null && authors.length > 0 && authors[0].name != null && !authors[0].name.isBlank()) {
            return authors[0].name;
        } else {
            return "Autor desconhecido";
        }
    }

    public int getAutorNasc() {
        return (authors != null && authors.length > 0 && authors[0].birth_year != null) ? authors[0].birth_year : 0;
    }

    public int getAutorFalec() {
        return (authors != null && authors.length > 0 && authors[0].death_year != null) ? authors[0].death_year : 0;
    }
}
