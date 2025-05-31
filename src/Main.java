import dominio.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Medico medico = new Medico("Ronaldo", "112113113", "algu@email.com", "aaaaaa","443242", "ui" );
        Paciente paciente = new Paciente("Alex", "111111111", "alex@gmail.com", "121212212111", "23/01/2033", Sexo.M);
        Tecnico tecnico = new Tecnico("aaaa", "dsadada", "asdsadad", "asdsadsad");
       
        medico.setEspecialidades("haha");
        medico.setEspecialidades("hihi");

        LocalDate data  = LocalDate.of(2025, 5, 30);
        medico.abrirAgenda(data);
        medico.abrirAgenda(LocalDate.of(2025, 6, 14));

        try {
            paciente.marcarConsulta(medico, LocalDate.of(2025, 6, 14), 10, TipoConsulta.RETORNO);

            paciente.marcarConsulta(medico, LocalDate.of(2025, 6, 14), 10, TipoConsulta.RETORNO);

        } catch (Exception e) {
            System.out.println("ERRO AO MARCAR CONSULTA");
            System.out.println(e.getMessage());
        }
        paciente.getUltimaConsulta().setDetalhes("detalhe");
        paciente.getUltimaConsulta().setSintoma("sintoma");

        Exame exame = new Exame("Exame bom", data, 10, tecnico, paciente);
        paciente.getUltimaConsulta().setExame(exame);

        System.out.println(medico.gerarProntuario(paciente.getUltimaConsulta()));
        
        paciente.irParaExame(exame);
        System.out.println(medico.gerarLaudo(exame));


        System.out.println(paciente.getHistoricoConsultas());
        System.out.println(paciente.getHistoricoExames());

    }
}