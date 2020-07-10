package com.example.estudando.entidades;

import android.widget.ImageView;

public class Curso {
    private String nome;
    private String site;
    private String especialidade;
    private String duracao;
    private String descricao;
    private String data;
    private String situacao;


    public Curso(String nome, String site, String especialidade, String duracao, String descricao, String data, String situacao) {
        this.nome = nome;
        this.site = site;
        this.especialidade = especialidade;
        this.duracao = duracao;
        this.descricao = descricao;
        this.data = data;
        this.situacao = situacao;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


}
