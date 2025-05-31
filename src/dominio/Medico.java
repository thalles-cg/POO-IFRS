package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Medico extends Responsavel{
    private String crm;
    private ArrayList<String> especialidades = new ArrayList<>();
    private ArrayList<Integer> disponibilidades = new ArrayList<>(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16, 17));
    private ArrayList<Consulta> consultas = new ArrayList<>();
    private HashMap<LocalDate, ArrayList<Integer>> agendaDisponivel = new HashMap<>();

    public Medico(String nome, String cpf, String email, String telefone, String crm, String especialidade) {
        super(nome, cpf, email, telefone);
        this.crm = crm;
        this.especialidades.add(especialidade);
    }

    public void abrirAgenda(LocalDate data) {
        Util.validarData(data);
        agendaDisponivel.put(data, new ArrayList<>(disponibilidades));
    }
    public void agendar(LocalDate data, int horario){
        agendaDisponivel.get(data).remove(Integer.valueOf(horario));
    }

    public String gerarProntuario(Consulta consulta){
        StringBuilder prontuario = new StringBuilder();

        prontuario.append("=== DETALHES DA CONSULTA ===\n");
        if (consulta.getDetalhes() != null && !consulta.getDetalhes().isEmpty()) {
            for (String detalhe : consulta.getDetalhes()) {
                prontuario.append("- ").append(detalhe).append("\n");
            }
        } else {
            prontuario.append("Nenhum detalhe registrado.\n");
        }

        prontuario.append("\n=== SINTOMAS ===\n");
        if (consulta.getSintomas() != null && !consulta.getSintomas().isEmpty()) {
            for (String sintoma : consulta.getSintomas()) {
                prontuario.append("- ").append(sintoma).append("\n");
            }
        } else {
            prontuario.append("Nenhum sintoma registrado.\n");
        }

        prontuario.append("\n=== EXAMES SOLICITADOS ===\n");
        if (consulta.getExames() != null && !consulta.getExames().isEmpty()) {
            for (Exame exame : consulta.getExames()) {
                prontuario.append("- ").append(exame.getNome()).append("\n");
            }
        } else {
            prontuario.append("Nenhum exame solicitado.\n");
        }
        consulta.setStatus(Status.CONCLUIDO);
        return prontuario.toString();
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

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }
    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }

    public HashMap<LocalDate, ArrayList<Integer>> getAgendaDisponivel() {
        return agendaDisponivel;
    }

    


    @Override
    public String toString() {
        return "Medico{" +
                "crm='" + crm + '\'' +
                ", especialidades=" + especialidades +
                ", consultas=" + consultas +
                ", horariosPorData=" + agendaDisponivel +
                '}';
    }
}
