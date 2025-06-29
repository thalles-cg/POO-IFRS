package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Consulta implements Agendavel{
    private LocalDate data;
    private int horario;
    private TipoConsulta tipoConsulta;
    private Medico medico;
    private Paciente paciente;
    private Prontuario prontuario;
    private Status status;

    Consulta(LocalDate data, int horario, TipoConsulta tipoConsulta, Medico medico, Paciente paciente) {
        this.data = data;
        this.horario = horario;
        this.tipoConsulta = tipoConsulta;
        this.medico = medico;
        this.paciente = paciente;
        this.status = Status.MARCADO;
        this.prontuario = new Prontuario();
    }

    public Prontuario getProntuario() {
        return this.prontuario;
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


    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
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
            return String.format(
            "%s - Tipo: %s - Médico: %s - Paciente: %s - Status: %s",
            Util.getData_horaFormatada(this.data, this.horario),
            this.getTipoConsulta(),
            this.getMedico().getNome(),
            this.getPaciente().getNome(),
            this.getStatus()
        );
    }
}
