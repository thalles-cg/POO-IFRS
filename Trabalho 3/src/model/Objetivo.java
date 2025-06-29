package model;

public class Objetivo {
    private Ponto posicao;

    public Objetivo(int x, int y) {
        this.posicao = new Ponto(x, y);
    }

    public Ponto getPosicao() {
        return posicao;
    }
}
