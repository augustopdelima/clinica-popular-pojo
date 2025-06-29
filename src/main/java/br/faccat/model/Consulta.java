package br.faccat.model;

import java.time.LocalDateTime;

public class Consulta {
    private String paciente;
    private LocalDateTime dataHora;
    public Consulta(String paciente, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.dataHora = dataHora;
    }
    public String getPaciente() {
        return paciente;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
