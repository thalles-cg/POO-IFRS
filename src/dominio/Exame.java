package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Exame implements Agendavel{

    private String nome;
    private String data_hora;
    private Paciente paciente;
    private Responsavel responsavel;
    private ArrayList<String> detalhesExame = new ArrayList<>();
    private Status status;
    private String laudo;

    public Exame(String nome, LocalDate data, int horario, Responsavel responsavel, Paciente paciente) {
        this.nome = nome;
        this.data_hora = horario+"h " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.responsavel = responsavel;
        this.paciente = paciente;
        this.status = Status.MARCADO;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public Responsavel getResponsavel() {
        return this.responsavel;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public ArrayList<String> getDetalhesExame() {
        return detalhesExame;
    }
    public void setDetalheExame(String detalheExame) {
        this.detalhesExame.add(detalheExame);
    }

    public String getLaudo() {
        if (laudo != null) return laudo;
        return null;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return String.format(
            "Exame: %s - Data/Hora: %s - Paciente: %s - Responsável: %s - Status: %s",
            this.nome,
            this.data_hora,
            this.paciente.getNome(),
            this.responsavel.getNome(),
            this.status
        );
    }

    
}
