package com.example.estudando.entidades;

import android.content.Intent;

public class Curso {
    private String especialidade;
    private String curso;
    private String idioma;
    //private String duracao;
    private String site;
    //private String imageUrl;
    private int imageUrl;
    private String imgDetalhesUrl;
    private String detalhes;
    private int imageid;
    private String fundacao;

    public Curso(String curso, int imageUrl, int imageid, String imgDetalhesUrl, String detalhes, String fundacao) {
        this.curso = curso;
        //this.especialidade = especialidade;
        //this.idioma = idioma;
        //this.duracao = duracao;
        //this.site = site;
        this.imageUrl = imageUrl;
        this.imageid = imageid;
        this.imgDetalhesUrl = imgDetalhesUrl;
        this.detalhes = detalhes;
        this.fundacao = fundacao;
    }

    public String getImgDetalhesUrl() {
        return imgDetalhesUrl;
    }

    public void setImgDetalhesUrl(String imgDetalhesUrl) {
        this.imgDetalhesUrl = imgDetalhesUrl;
    }

    public String getFundacao() {
        return fundacao;
    }

    public void setFundacao(String fundacao) {
        this.fundacao = fundacao;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /*public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }*/

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
