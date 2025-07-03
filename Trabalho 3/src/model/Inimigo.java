package model;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Inimigo extends Personagem implements Animavel {
    private SpriteSheet animacao;
    private final Random random = new Random();
    private long ultimoMovimento = 0;
    private final int intervaloMovimento = 300;

    public Inimigo(int x, int y, int velocidade, BufferedImage spritesheet) {
        super(x, y, velocidade);
        this.animacao = new SpriteSheet(spritesheet, 48, 48, 6, 10);
    }

    @Override
    public void atualizar() {
        atualizarAnimacao();
        long agora = System.currentTimeMillis();
        if (agora - ultimoMovimento >= intervaloMovimento) {
            moverAleatorio();
            ultimoMovimento = agora;
        }
    }

    public void moverAleatorio() {
        int valorAleatorio = random.nextInt(4);
        Direcao direcao = Direcao.fromValor(valorAleatorio);
        mover(direcao);
    }

    @Override
    public void atualizarAnimacao() {
        animacao.atualizar();
    }

    @Override
    public BufferedImage getImagemAtual() {
        return animacao.getFrameAtual();
    }
}

