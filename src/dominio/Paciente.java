package dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Pessoa{
    private String dataNascimento;
    private Sexo sexo;
    private ArrayList<Consulta> historicoConsultas = new ArrayList<>();
    private ArrayList<Exame> historicoExames = new ArrayList<>();

    public Paciente(String nome, String cpf, String email, String telefone, String dataNascimento, Sexo sexo) {
        super(nome, cpf, email, telefone);
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public void marcarConsulta(Medico medico,LocalDate data, int horario, TipoConsulta tipoConsulta) throws Exception {
        if (!historicoConsultas.isEmpty() && historicoConsultas.getLast().getStatus() == Status.MARCADO) {
            return;
        }

        Util.validarDataHorario(medico, data, horario);
        Consulta consulta = new Consulta(data, horario, tipoConsulta, medico, this);
        medico.agendar(data, horario);
        this.historicoConsultas.add(consulta);
    }

    public void irParaExame(Exame exame) {
        exame.setStatus(Status.CONCLUIDO);
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public ArrayList<Consulta> getHistoricoConsultas() {
        return this.historicoConsultas;
    }
    public void setHistoricoConsultas(Consulta consulta) {
        this.historicoConsultas.add(consulta);
    }

    public Consulta getUltimaConsulta(){
        if (historicoConsultas.isEmpty()){
            throw new RuntimeException("Consulta está vazia");
        }
        return this.historicoConsultas.get(historicoConsultas.size() - 1);
    }

    

    public ArrayList<Exame> getHistoricoExames() {
        return historicoExames;
    }

    public void setHistoricoExame(Exame exame) {
        this.historicoExames.add(exame);
    }

    @Override
    public String toString() {
        return "Paciente [dataNascimento=" + dataNascimento + ", sexo=" + sexo + "]";
    }
}
