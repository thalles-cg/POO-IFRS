package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Exame implements Agendavel{

    private String nome;
    private LocalDate data;
    private int horario;
    private Paciente paciente;
    private Responsavel responsavel;
    private ArrayList<String> detalhesExame = new ArrayList<>();
    private Status status;
    private String laudo;

    Exame(String nome, Paciente paciente) {
        this.nome = nome;
        this.paciente = paciente;
        this.status = Status.NAO_REALIZADO;
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

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public int getHorario() {
        return horario;
    }
    public void setHorario(int horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        if (this.responsavel == null){
            return String.format(
                    "Exame: %s - Paciente: %s - Status: %s",
                    this.nome,
                    this.paciente.getNome(),
                    this.status
            );
        }
        return String.format(
            "Exame: %s - Data/Hora: %s - Paciente: %s - Responsável: %s - Status: %s",
            this.nome,
            Util.getData_horaFormatada(this.data, this.horario),
            this.paciente.getNome(),
            this.responsavel.getNome(),
            this.status
        );
    }

    
}
