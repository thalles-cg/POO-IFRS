package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Medico extends Responsavel{
    private String crm;
    private ArrayList<String> especialidades = new ArrayList<>();
    private ArrayList<Integer> disponibilidades = new ArrayList<>(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16, 17));
    private ArrayList<Consulta> consultasMarcadas = new ArrayList<>();
    private HashMap<LocalDate, ArrayList<Integer>> agendaDisponivel = new HashMap<>();

    public Medico(String nome, String cpf, String email, String telefone, String crm, String especialidade) {
        super(nome, cpf, email, telefone);
        this.crm = crm;
        this.especialidades.add(especialidade);
    }

    public void abrirAgenda(LocalDate data) {
        try{
            Util.validarData(data);
        } catch (Exception e) {
            System.out.print("ERRO: ");
            System.out.println(e.getMessage());
        }
        agendaDisponivel.put(data, new ArrayList<>(disponibilidades));
    }
    public void agendar(LocalDate data, int horario){
        agendaDisponivel.get(data).remove(Integer.valueOf(horario));
    }

    public Consulta getConsulta(LocalDate data, int horario) throws Exception{
        for (Consulta consulta : this.consultasMarcadas){
            if (consulta.getData().equals(data) && consulta.getHorario() == horario){
                return consulta;
            }
        }
        throw new DataInvalidaException("Nenhuma consulta encontrada para " + Util.getData_horaFormatada(data, horario));
    }

    public void adicionarDetalheConsulta(LocalDate data, int horario, String detalhe) throws Exception {
        Consulta consulta = getConsulta(data, horario);
        consulta.getProntuario().adicionarDetalhes(detalhe);
    }

    public void adicionarSintomaConsulta(LocalDate data, int horario, String sintoma) throws Exception {
        Consulta consulta = getConsulta(data, horario);
        consulta.getProntuario().adicionarSintomas(sintoma);
    }

    public void adicionarExameSolicitadoConsulta(LocalDate data, int horario, String exame) throws Exception {
        Consulta consulta = getConsulta(data, horario);
        consulta.getProntuario().adicionarExame(exame);
    }
    public void adicionarMedicamentoConsulta(LocalDate data, int horario, String medicamento) throws Exception {
        Consulta consulta = getConsulta(data, horario);
        consulta.getProntuario().adicionarMedicamento(medicamento);
    }

    public void encerrarConsulta(Consulta consulta){
        if (consulta.getStatus() == Status.CONCLUIDO) return;
        consulta.setStatus(Status.CONCLUIDO);
        consulta.getPaciente().getConsultasMarcadas().remove(consulta);
        consulta.getPaciente().adicionarHistoricoConsulta(consulta);
    }
    
    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }

    public ArrayList<String> getEspecialidades() {
        return especialidades;
    }
    public void setEspecialidades(String especialidade) {
        this.especialidades.add(especialidade);
    }

    public ArrayList<Consulta> getConsultasMarcadas() {
        return consultasMarcadas;
    }
    public void setConsultaMarcada(Consulta consultaMarcada) {
        this.consultasMarcadas.add(consultaMarcada);
    }

    public HashMap<LocalDate, ArrayList<Integer>> getAgendaDisponivel() {
        return agendaDisponivel;
    }

    


    @Override
    public String toString() {
        return "Medico{" +
                "crm='" + crm + '\'' +
                ", especialidades=" + especialidades +
                ", consultas=" + consultasMarcadas +
                ", horariosPorData=" + agendaDisponivel +
                '}';
    }
}
