package dominio;

import java.util.ArrayList;

public class Prontuario {
    private ArrayList<String> detalhesConsulta = new ArrayList<>();
    private ArrayList<String> sintomas = new ArrayList<>();
    private ArrayList<String> examesSolicitados = new ArrayList<>();
    private ArrayList<String> medicamentos = new ArrayList<>();

    Prontuario(){
    }

    public ArrayList<String> getDetalhesConsulta() {
        return detalhesConsulta;
    }
    public void adicionarDetalhes(String detalheConsulta){
        this.detalhesConsulta.add(detalheConsulta);
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }
    public void adicionarSintomas(String sintoma){
        this.sintomas.add(sintoma);
    }

    public ArrayList<String> getExamesSolicitados() {
        return examesSolicitados;
    }
    public void adicionarExame(String exame){
        this.examesSolicitados.add(exame);
    }

    public ArrayList<String> getMedicamentos() {
        return medicamentos;
    }
    public void adicionarMedicamento(String medicamento){
        this.medicamentos.add(medicamento);
    }

    public String formatarTexto() {
        StringBuilder prontuario = new StringBuilder();

        prontuario.append("=== DETALHES DA CONSULTA ===\n");
        if (!detalhesConsulta.isEmpty()) {
            for (String detalhe : detalhesConsulta) {
                prontuario.append("- ").append(detalhe).append("\n");
            }
        } else {
            prontuario.append("Nenhum detalhe registrado.\n");
        }

        prontuario.append("\n=== SINTOMAS ===\n");
        if (!sintomas.isEmpty()) {
            for (String sintoma : sintomas) {
                prontuario.append("- ").append(sintoma).append("\n");
            }
        } else {
            prontuario.append("Nenhum sintoma registrado.\n");
        }

        prontuario.append("\n=== EXAMES SOLICITADOS ===\n");
        if (!examesSolicitados.isEmpty()) {
            for (String exame : examesSolicitados) {
                prontuario.append("- ").append(exame).append("\n");
            }
        } else {
            prontuario.append("Nenhum exame solicitado.\n");
        }

        prontuario.append("\n=== MEDICAMENTOS PRESCRITOS ===\n");
        if (!medicamentos.isEmpty()) {
            for (String medicamento : medicamentos) {
                prontuario.append("- ").append(medicamento).append("\n");
            }
        } else {
            prontuario.append("Nenhum medicamento prescrito.\n");
        }

        return prontuario.toString();
    }


    @Override
    public String toString() {
        return "Prontuario{" +
                "detalhesConsulta=" + detalhesConsulta +
                ", sintomas=" + sintomas +
                ", examesSolicitados=" + examesSolicitados +
                ", medicamentos=" + medicamentos +
                '}';
    }
}
