package br.faccat.service;

import br.faccat.model.Consulta;
import br.faccat.model.Paciente;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultaService {

    private final Map<String, Paciente> pacientes = new HashMap<>();

    public void agendarConsulta(String nome, String cpf, String dataHoraTexto) {
        System.out.println("Agendando consulta para " + nome + " (CPF: " + cpf + ") em " + dataHoraTexto);
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraTexto);
        Consulta consulta = new Consulta(nome, dataHora);

        Paciente paciente = pacientes.get(cpf);
        if (paciente == null) {
            paciente = new Paciente(nome, cpf);
            pacientes.put(cpf, paciente);
        }

        paciente.adicionarConsulta(consulta);
    }


    public Map<String, Paciente> getPacientes() {
        return pacientes;
    }

    public List<Consulta> getConsultasPorCpf(String cpf) {
        Paciente paciente = pacientes.get(cpf);
        if (paciente != null) {
            return paciente.getConsultas();
        }
        return new ArrayList<>();
    }

    public void listarConsultasPorCpf(String cpf) {
        Paciente paciente = pacientes.get(cpf);
        if (paciente == null) {
            System.out.println("Nenhum paciente encontrado com CPF: " + cpf);
            return;
        }

        System.out.println("Consultas para " + paciente.getNome() + " (CPF: " + paciente.getCpf() + "):");
        for (Consulta c : paciente.getConsultas()) {
            System.out.println("- " + c.getPaciente() + " em " + c.getDataHora());
        }
    }



}
