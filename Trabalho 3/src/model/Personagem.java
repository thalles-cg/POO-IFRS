package model;

public abstract class Personagem implements Controlavel {
    protected Ponto posicao;
    protected int velocidade;

    public Personagem(int x, int y, int velocidade) {
        this.posicao = new Ponto(x, y);
        this.velocidade = velocidade;
    }

    public abstract void atualizar();

    @Override
    public void mover(Direcao direcao) {
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