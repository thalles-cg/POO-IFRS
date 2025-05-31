package dominio;

import java.util.ArrayList;

public class Prontuario {
    private ArrayList<String> detalhesConsulta = new ArrayList<>();
    private ArrayList<String> sintomas = new ArrayList<>();
    private ArrayList<Exame> examesSolicitados = new ArrayList<>();

    public Prontuario(ArrayList<String> detalhesConsulta, ArrayList<String> sintomas, ArrayList<Exame> examesSolicitados) {
        this.detalhesConsulta = detalhesConsulta;
        this.sintomas = sintomas;
        this.examesSolicitados = examesSolicitados;
    }

    public ArrayList<String> getDetalhesConsulta() {
        return detalhesConsulta;
    }
    public void setDetalheConsulta(String detalheConsulta) {
        this.detalhesConsulta.add(detalheConsulta);
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }
    public void setSintoma(String sintoma) {
        this.sintomas.add(sintoma);
    }

    public ArrayList<Exame> getExamesSolicitados() {
        return examesSolicitados;
    }
    public void setExameSolicitado(Exame exameSolicitado) {
        this.examesSolicitados.add(exameSolicitado);
    }
}
