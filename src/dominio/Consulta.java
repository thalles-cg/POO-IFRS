package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Consulta implements Agendavel{
    private String data_hora;
    private TipoConsulta tipoConsulta;
    private Medico medico;
    private Paciente paciente;
    private Prontuario prontuario;
    private Status status;
    private ArrayList<String> detalhes = new ArrayList<>();
    private ArrayList<String> sintomas = new ArrayList<>();
    private ArrayList<Exame> exames = new ArrayList<>();

    public Consulta(LocalDate data, int horario, TipoConsulta tipoConsulta, Medico medico, Paciente paciente) {
        this.data_hora = horario+"h " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.tipoConsulta = tipoConsulta;
        this.medico = medico;
        this.paciente = paciente;
        this.status = Status.MARCADO;
    }

    public Prontuario getProntuario() {
        if(this.status == Status.CONCLUIDO) return prontuario;
        return null;
    }

    @Override
    public Responsavel getResponsavel() {
        return this.medico;
    }

    @Override
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getData_hora() {
        return data_hora;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public ArrayList<String> getDetalhes() {
        return this.detalhes;
    }
    public void setDetalhes(String detalhe) {
        this.detalhes.add(detalhe);
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }
    public void setSintoma(String sintoma) {
        this.sintomas.add(sintoma);
    }

    public ArrayList<Exame> getExames() {
        return exames;
    }
    public void setExame(Exame exame) {
        this.exames.add(exame);
    }

    @Override
    public String toString() {
            return String.format(
            "Consulta em %s - Tipo: %s - Médico: %s - Status: %s",
            this.getData_hora(), 
            this.getTipoConsulta(),
            this.getMedico().getNome(), 
            this.getStatus()
        );
    }
}
