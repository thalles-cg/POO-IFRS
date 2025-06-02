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
        laudo.append("Data/Hora: ").append(exame.getData_hora()).append("\n");
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
        exame.getPaciente().setHistoricoExame(exame);
        exame.setStatus(Status.CONCLUIDO); 
    
        return laudo.toString();
    }

    public void encerrarExame(LocalDate data, int horario, Paciente paciente) {

    }

}
