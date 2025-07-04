package model;

import java.awt.*;

public abstract class Personagem implements Controlavel {
    protected Ponto posicao;
    protected int velocidade;

    public Personagem(int x, int y, int velocidade) {
        this.posicao = new Ponto(x, y);
        this.velocidade = velocidade;
    }

    public abstract void atualizar();

    public void mover(Direcao direcao) {
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraSprite = getLarguraSprite() * getEscalaSprite();
        int alturaSprite = getAlturaSprite() * getEscalaSprite();

        int novaX = posicao.x;
        int novaY = posicao.y;

        switch (direcao) {
            case CIMA: novaY -= velocidade; break;
            case BAIXO: novaY += velocidade; break;
            case ESQUERDA: novaX -= velocidade; break;
            case DIREITA: novaX += velocidade; break;
        }

        if (novaX >= 0 && novaX + larguraSprite <= tamanhoTela.width) {
            posicao.x = novaX;
        }
        if (novaY >= 0 && novaY + alturaSprite <= tamanhoTela.height) {
            posicao.y = novaY;
        }
    }


    public Ponto getPosicao() {
        return posicao;
    }

    public abstract int getLarguraSprite();
    public abstract int getAlturaSprite();
    public abstract int getEscalaSprite();
}