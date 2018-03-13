package br.edu.iff.pooa20172.banco_dados.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Servico extends RealmObject{
    @PrimaryKey
    private int id;
    private String nome;
    private int horas;
    private String mecanico;

    public Servico(){}

    public Servico(int id, String nome, int horas, String mecanico){
        this.id = id;
        this.nome = nome;
        this.horas = horas;
        this.mecanico = mecanico;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNome() {return nome;}[]
    public void setNome(String nome) {this.nome = nome;}
    public int getHoras() {return horas;}
    public void setHoras(int horas) {this.horas = horas;}
    public String getMecanico() {return mecanico;}
    public void setMecanico(String mecanico) {this.mecanico = mecanico;}
}
