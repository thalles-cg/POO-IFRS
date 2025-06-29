package dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Pessoa{
    private String dataNascimento;
    private Sexo sexo;
    private ArrayList<Consulta> historicoConsultas = new ArrayList<>();
    private ArrayList<Consulta> consultasMarcadas = new ArrayList<>();
    private ArrayList<Exame> historicoExames = new ArrayList<>();
    private ArrayList<Exame> examesSolicitados = new ArrayList<>();

    public Paciente(String nome, String cpf, String email, String telefone, String dataNascimento, Sexo sexo) {
        super(nome, cpf, email, telefone);
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public void marcarConsulta(Medico medico,LocalDate data, int horario, TipoConsulta tipoConsulta) throws Exception {
        Util.validarDataHorario(medico, data, horario);
        Consulta consulta = new Consulta(data, horario, tipoConsulta, medico, this);
        medico.agendar(data, horario);
        medico.setConsultaMarcada(consulta);
        this.consultasMarcadas.add(consulta);
    }

    public Exame buscarExame(String nome) throws Exception{
        for (Exame exame : this.examesSolicitados) {
            if (exame.getNome().equals(nome)){
                return exame;
            }
        }
        throw new ExameNaoExiste("Nome do exame inválido. Tente novamente!");
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
        return historicoConsultas;
    }
    public void adicionarHistoricoConsulta(Consulta historicoConsulta) {
        this.historicoConsultas.add(historicoConsulta);
    }

    public ArrayList<Consulta> getConsultasMarcadas() {
        return consultasMarcadas;
    }
    public void setConsultasMarcadas(ArrayList<Consulta> consultasMarcadas) {
        this.consultasMarcadas = consultasMarcadas;
    }

    public ArrayList<Exame> getHistoricoExames() {
        return historicoExames;
    }

    public void setHistoricoExame(Exame exame) {
        this.historicoExames.add(exame);
    }

    public void adicionarHistoricoExame(Exame historicoExame) {
        this.historicoExames.add(historicoExame);
    }

    public ArrayList<Exame> getExamesSolicitados() {
        return examesSolicitados;
    }
    public void adicionarExameSolicitado(Exame examesSolicitado) {
        this.examesSolicitados.add(examesSolicitado);
    }

    public Consulta getConsultaMarcada(LocalDate data, int horario) throws Exception{
        for (Consulta consulta : consultasMarcadas){
            if (consulta.getData().equals(data) && consulta.getHorario() == horario){
                return consulta;
            }
        }
        throw new DataInvalidaException("Não existe nenhuma consulta marcada em " + data + " as " + horario);
    }
    @Override
    public String toString() {
        return "Paciente [dataNascimento=" + dataNascimento + ", sexo=" + sexo + "]";
    }
}
