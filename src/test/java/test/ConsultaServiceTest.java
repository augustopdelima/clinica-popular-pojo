package test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.faccat.service.ConsultaService;
import br.faccat.model.Consulta;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultaServiceTest {
    private ConsultaService service;

    @BeforeEach
    void setup() {
        service = new ConsultaService();
    }

    @Test
    void listaDeveComecarVazia() {
        List<Consulta> consultas = service.getConsultasPorCpf("11122233344");
        assertTrue(consultas.isEmpty());
    }

    @Test
    void deveAgendarConsultaParaNovoPaciente() {
        service.agendarConsulta("Ana", "11122233344", "2025-06-25T10:00");

        List<Consulta> consultas = service.getConsultasPorCpf("11122233344");
        assertEquals(1, consultas.size());
        assertEquals("Ana", consultas.get(0).getPaciente());
    }

    @Test
    void deveAdicionarMultiplasConsultasAoMesmoPaciente() {
        service.agendarConsulta("João", "55566677788", "2025-06-25T09:00");
        service.agendarConsulta("João", "55566677788", "2025-06-26T14:00");

        List<Consulta> consultas = service.getConsultasPorCpf("55566677788");
        assertEquals(2, consultas.size());
        assertEquals("João", consultas.get(0).getPaciente());
        assertEquals("João", consultas.get(1).getPaciente());
    }

    @Test
    void deveCriarPacienteComSucesso() {
        service.agendarConsulta("Joana", "12312312300", "2025-07-01T09:30");

        // Verifica se o paciente existe no mapa
        assertTrue(service.getPacientes().containsKey("12312312300"));

        var paciente = service.getPacientes().get("12312312300");

        assertEquals("Joana", paciente.getNome());
        assertEquals("12312312300", paciente.getCpf());
    }

    @Test
    void deveManterPacientesSeparados() {
        service.agendarConsulta("João", "55566677788", "2025-06-25T09:00");
        service.agendarConsulta("Maria", "99988877766", "2025-06-25T11:00");

        List<Consulta> consultasJoao = service.getConsultasPorCpf("55566677788");
        List<Consulta> consultasMaria = service.getConsultasPorCpf("99988877766");

        assertEquals(1, consultasJoao.size());
        assertEquals(1, consultasMaria.size());

        assertEquals("João", consultasJoao.get(0).getPaciente());
        assertEquals("Maria", consultasMaria.get(0).getPaciente());
    }

    @Test
    void deveImprimirConsultasDoPaciente() {
        // Redirecionar System.out para capturar a saída
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        // Dados de entrada
        String cpf = "11122233344";
        service.agendarConsulta("Ana", cpf, "2025-06-25T10:00");
        service.agendarConsulta("Ana", cpf, "2025-07-01T14:00");

        // Executar listagem
        service.listarConsultasPorCpf(cpf);

        // Restaurar System.out
        System.setOut(originalOut);

        // Verificar saída
        String resultado = output.toString();
        assertTrue(resultado.contains("Consultas para Ana (CPF: 11122233344)"));
        assertTrue(resultado.contains("2025-06-25T10:00"));
        assertTrue(resultado.contains("2025-07-01T14:00"));
    }
}



