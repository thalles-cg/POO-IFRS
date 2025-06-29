package model;

public abstract class Personagem implements Controlavel {
    protected Ponto posicao;
    protected int velocidade;

    public static final int CIMA = 0;
    public static final int BAIXO = 1;
    public static final int ESQUERDA = 2;
    public static final int DIREITA = 3;

    public Personagem(int x, int y, int velocidade) {
        this.posicao = new Ponto(x, y);
        this.velocidade = velocidade;
    }

    public abstract void atualizar();

    @Override
    public void mover(int direcao) {
        switch (direcao) {
            case CIMA: posicao.y -= velocidade; break;
            case BAIXO: posicao.y += velocidade; break;
            case ESQUERDA: posicao.x -= velocidade; break;
            case DIREITA: posicao.x += velocidade; break;
        }
    }

    public Ponto getPosicao() {
        return posicao;
    }
}