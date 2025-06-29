package dominio;

import java.time.LocalDate;

public class Responsavel extends Pessoa{
    public Responsavel(String nome, String cpf, String email, String telefone) {
        super(nome, cpf, email, telefone);
    }

    public String gerarLaudo(Exame exame) {
        if (exame.getStatus() != Status.CONCLUIDO){
            throw new ExameNaoTerminadoException("Exame não foi concluído");
        }
        StringBuilder laudo = new StringBuilder();
    
        laudo.append("=== LAUDO DO EXAME ===\n");
        laudo.append("Paciente: ").append(exame.getPaciente().getNome()).append("\n");
        laudo.append("Exame: ").append(exame.getNome()).append("\n");
        laudo.append("Data/Hora: ").append(Util.getData_horaFormatada(exame.getData(), exame.getHorario())).append("\n");
        laudo.append("Responsável: ").append(exame.getResponsavel().getNome()).append("\n\n");
    
        laudo.append("=== DETALHES DO EXAME ===\n");
        if (exame.getDetalhesExame() != null && !exame.getDetalhesExame().isEmpty()) {
            for (String detalhe : exame.getDetalhesExame()) {
                laudo.append("- ").append(detalhe).append("\n");
            }
        } else {
            laudo.append("Nenhum detalhe registrado.\n");
        }
    
        exame.setLaudo(laudo.toString());
        return laudo.toString();
    }

    public void encerrarExame(LocalDate data, int horario, Paciente paciente, Exame exame) throws Exception{
        for (Exame exameSolicitado : paciente.getExamesSolicitados()){
            if (exameSolicitado.equals(exame)){
                exame.setData(data);
                exame.setHorario(horario);
                exame.setStatus(Status.CONCLUIDO);
                exame.setResponsavel(this);
                atualizarExamePaciente(paciente, exame);
                return;
            }
        }
       throw new ExameNaoExiste("Nome do exame inválido. Tente novamente!");
    }

    public void atualizarExamePaciente(Paciente paciente, Exame exame){
        exame.setStatus(Status.CONCLUIDO);
        paciente.getExamesSolicitados().remove(exame);
        paciente.adicionarHistoricoExame(exame);
    }


}
