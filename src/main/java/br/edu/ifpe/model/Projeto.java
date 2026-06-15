package br.edu.ifpe.model;

import java.util.List;

public class Projeto {
    private String titulo;
    private String resumo;
    private List<String> palavrasChave;
    private String publicoAlvo;
    private String areaTematica;
    private String campus;
    private List<String> ods;
    private boolean aceiteTermoCompromisso;
    private String status;

    public Projeto() {
        this.status = "Rascunho";
    }

    public void editarProjeto(Projeto novosDados) {
        if (!"Rascunho".equalsIgnoreCase(this.status) && !"Em Correção".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Não é possível editar o projeto. Status atual: " + this.status);
        }
        this.titulo = novosDados.getTitulo();
        this.resumo = novosDados.getResumo();
        this.palavrasChave = novosDados.getPalavrasChave();
        this.publicoAlvo = novosDados.getPublicoAlvo();
        this.areaTematica = novosDados.getAreaTematica();
        this.campus = novosDados.getCampus();
        this.ods = novosDados.getOds();
        this.aceiteTermoCompromisso = novosDados.isAceiteTermoCompromisso();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public List<String> getOds() {
        return ods;
    }

    public void setOds(List<String> ods) {
        this.ods = ods;
    }

    public boolean isAceiteTermoCompromisso() {
        return aceiteTermoCompromisso;
    }

    public void setAceiteTermoCompromisso(boolean aceiteTermoCompromisso) {
        this.aceiteTermoCompromisso = aceiteTermoCompromisso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
