import dominio.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Medico medico = new Medico("Ronaldo", "112113113", "algu@email.com", "aaaaaa","443242", "ui" );
        Paciente paciente = new Paciente("Alex", "111111111", "alex@gmail.com", "121212212111", "23/01/2033", Sexo.M);
        Tecnico tecnico = new Tecnico("aaaa", "dsadada", "asdsadad", "asdsadsad");
       
        medico.setEspecialidades("haha");
        medico.setEspecialidades("hihi");

        LocalDate data  = LocalDate.now();
        medico.abrirAgenda(data);
        medico.abrirAgenda(LocalDate.of(2024, 6, 14));

        try {
            paciente.marcarConsulta(medico, LocalDate.now(), 10, TipoConsulta.RETORNO);
            paciente.marcarConsulta(medico, LocalDate.now(), 11, TipoConsulta.RETORNO);
        } catch (Exception e) {
            System.out.print("ERRO: ");
            System.out.println(e.getMessage());
        }

        System.out.println(medico.getConsultasMarcadas());
        try {
            System.out.println(medico.getConsulta(data, 10));

        } catch (Exception e) {
            System.out.print("ERRO: ");
            System.out.println(e.getMessage());
        }

        try{
            medico.adicionarSintomaConsulta(data, 10, "dor :(");

            medico.adicionarExameSolicitadoConsulta(data, 10, "Exame urgente");
        } catch (Exception e){
            System.out.print("ERRO: ");
            System.out.println(e.getMessage());
        }

        try {
            medico.encerrarConsulta(medico.getConsulta(data, 10));
            medico.encerrarConsulta(medico.getConsulta(data, 10));
            System.out.println(medico.getConsulta(data, 10).getProntuario().formatarTexto());
            System.out.println(medico.getConsulta(data, 10).getProntuario());

        } catch (Exception e) {
            System.out.print("ERRO: ");
            System.out.println(e.getMessage());
        }


//        paciente.irParaExame(exame);
//        System.out.println(medico.gerarLaudo(exame));
//
//
        System.out.println(paciente.getHistoricoConsultas());
//        System.out.println(paciente.getHistoricoExames());

    }
}