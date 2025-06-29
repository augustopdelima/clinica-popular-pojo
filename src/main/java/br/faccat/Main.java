package br.faccat;

import br.faccat.service.ConsultaService;

public class Main {
    public static void main(String[] args) {
        ConsultaService service = new ConsultaService();


        service.agendarConsulta("João", "12345678900", "2025-06-30T09:00");
        service.agendarConsulta("Maria", "98765432100", "2025-07-01T14:00");


        service.agendarConsulta("João", "12345678900", "2025-07-05T11:00");

        System.out.println("\n--- Listando apenas as consultas do João ---");
        service.listarConsultasPorCpf("12345678900");
    }
}
