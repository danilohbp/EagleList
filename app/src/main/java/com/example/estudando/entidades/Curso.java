package com.example.estudando.entidades;

public class Curso {
    private String especialidade;
    private String curso;
    private String idioma;
    private String duracao;
    private String site;
    private int icon;
    private int imageid;

    public Curso(){}

    public Curso(String curso, String especialidade, String idioma, String duracao, String site, int imageid){
        this.curso = curso;
        this.especialidade = especialidade;
        this.idioma = idioma;
        this.duracao = duracao;
        this.site = site;
        this.imageid = imageid;
    }


    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCurso() {
        return curso;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
