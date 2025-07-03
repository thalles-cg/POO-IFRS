package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Inimigo extends Personagem implements Animavel {
    private Animador animador;
    private final Random random = new Random();
    private long ultimoMovimento = 0;
    private final int intervaloMovimento = 300;
    private boolean movendo;
    private boolean viradoParaEsquerda = false;

    private final int LARGURA_SPRITE = 48;
    private final int ALTURA_SPRITE = 48;
    private final int ESCALA_SPRITE = 3;

    public Inimigo(int x, int y, int velocidade, BufferedImage spritesheet) {
        super(x, y, velocidade);
        animador = new Animador();
        animador.adicionarAnimacao(EstadoAnimacao.PARADO, new SpriteSheet(spritesheet, LARGURA_SPRITE, ALTURA_SPRITE, 6, 10));
        animador.adicionarAnimacao(EstadoAnimacao.ANDANDO, new SpriteSheet(spritesheet, LARGURA_SPRITE, ALTURA_SPRITE, 6, 10));
        animador.setEstado(EstadoAnimacao.PARADO);
    }

    @Override
    public void atualizar() {
        atualizarAnimacao();
        long agora = System.currentTimeMillis();
        if (agora - ultimoMovimento >= intervaloMovimento) {
            moverAleatorio();
            ultimoMovimento = agora;
        } else {
            movendo = false;
        }
        if (!movendo) {
            animador.setEstado(EstadoAnimacao.PARADO);
        }
    }

    public void moverAleatorio() {
        int valorAleatorio = random.nextInt(4);
        Direcao direcao = Direcao.fromValor(valorAleatorio);
        mover(direcao);
    }

    @Override
    public void mover(Direcao direcao) {
        super.mover(direcao);
        animador.setEstado(EstadoAnimacao.ANDANDO);
        movendo = true;
        if (direcao == Direcao.ESQUERDA) viradoParaEsquerda = true;
        else if (direcao == Direcao.DIREITA) viradoParaEsquerda = false;
    }

    @Override
    public void atualizarAnimacao() {
        animador.atualizar();
    }

    @Override
    public BufferedImage getImagemAtual() {
        return animador.getFrameAtual();
    }

    public boolean isViradoParaEsquerda() {
        return viradoParaEsquerda;
    }

    public Rectangle getRetanguloColisao() {
        int largura = LARGURA_SPRITE * ESCALA_SPRITE;
        int altura = ALTURA_SPRITE * ESCALA_SPRITE;
        int offsetX = (largura - 20) / 2;
        int offsetY = (altura - 20) / 2;
        return new Rectangle(posicao.x + offsetX, posicao.y + offsetY, 20, 20);
    }

    public int getEscalaSprite() {
        return ESCALA_SPRITE;
    }

    public int getLarguraSprite() {
        return LARGURA_SPRITE;
    }

    public int getAlturaSprite() {
        return ALTURA_SPRITE;
    }
}
