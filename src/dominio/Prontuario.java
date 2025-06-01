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
