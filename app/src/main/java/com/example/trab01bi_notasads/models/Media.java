package com.example.trab01bi_notasads.models;

public class Media {

    private String nome;
    private String email;
    private int idade;
    private String disciplina;
    private double notaPrimBi;
    private double notaSegBi;
    private double media;
    private boolean aprovado;

    public Media(String nome, String email, int idade, String disciplina, double notaPrimBi, double notaSegBi) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.disciplina = disciplina;
        this.notaPrimBi = notaPrimBi;
        this.notaSegBi = notaSegBi;
        this.media = calcularMedia();
        this.aprovado = aprovacao();
    }

    private double calcularMedia() {
        return (notaPrimBi + notaSegBi) / 2;
    }

    private boolean aprovacao(){
        if(media >= 6){
            return true;
        }else {
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public double getNotaPrimBi() {
        return notaPrimBi;
    }

    public double getNotaSegBi() {
        return notaSegBi;
    }

    public double getMedia() {
        return media;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setNotaPrimBi(double notaPrimBi) {
        this.notaPrimBi = notaPrimBi;
    }

    public void setNotaSegBi(double notaSegBi) {
        this.notaSegBi = notaSegBi;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    @Override
    public String toString() {
        return "Notas{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", disciplina='" + disciplina + '\'' +
                ", notaPrimBi=" + notaPrimBi +
                ", notaSegBi=" + notaSegBi +
                ", media=" + media +
                ", aprovado=" + aprovado +
                '}';
    }
}
