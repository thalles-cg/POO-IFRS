package dominio;

import java.util.ArrayList;

public class Prontuario {
    private ArrayList<String> detalhesConsulta = new ArrayList<>();
    private ArrayList<String> sintomas = new ArrayList<>();
    private ArrayList<Exame> examesSolicitados = new ArrayList<>();

    Prontuario(){
    }

    public ArrayList<String> getDetalhesConsulta() {
        return detalhesConsulta;
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }

    public ArrayList<Exame> getExamesSolicitados() {
        return examesSolicitados;
    }
}
