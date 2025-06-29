package br.faccat.model;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
   private String nome;
   private String cpf;
   private List<Consulta> consultas = new ArrayList<>();

    public Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Paciente(List<Consulta> consultas, String cpf, String nome) {
        this.consultas = consultas;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
}
