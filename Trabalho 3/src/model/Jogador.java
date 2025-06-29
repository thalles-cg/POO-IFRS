package model;

public class Jogador {
    private Ponto posicao;
    private int velocidade = 5;

    public Jogador(int x, int y) {
        this.posicao = new Ponto(x, y);
    }

    public void moverCima() {
        posicao.y -= velocidade;
    }

    public void moverBaixo() {
        posicao.y += velocidade;
    }

    public void moverEsquerda() {
        posicao.x -= velocidade;
    }

    public void moverDireita() {
        posicao.x += velocidade;
    }

    public Ponto getPosicao() {
        return posicao;
    }
}

