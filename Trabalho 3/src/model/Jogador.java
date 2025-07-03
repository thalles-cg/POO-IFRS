package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Jogador extends Personagem implements Animavel {
    private static final int LARGURA_SPRITE = 50;
    private static final int ALTURA_SPRITE = 50;
    private static final int ESCALA_SPRITE = 3;

    private final Animador animador;
    private boolean movendo;
    private boolean viradoParaEsquerda = false;

    public Jogador(int x, int y, int velocidade, BufferedImage paradoSheet, BufferedImage andandoSheet) {
        super(x, y, velocidade);

        animador = new Animador();
        animador.adicionarAnimacao(EstadoAnimacao.PARADO, new SpriteSheet(paradoSheet, LARGURA_SPRITE, ALTURA_SPRITE, 10, 5));
        animador.adicionarAnimacao(EstadoAnimacao.ANDANDO, new SpriteSheet(andandoSheet, LARGURA_SPRITE, ALTURA_SPRITE, 8, 5));
        animador.setEstado(EstadoAnimacao.PARADO);
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
    public void atualizar() {
        atualizarAnimacao();
        if (!movendo) {
            animador.setEstado(EstadoAnimacao.PARADO);
        }
        movendo = false;
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

    public int getEscalaSprite() {
        return ESCALA_SPRITE;
    }

    public Rectangle getRetanguloColisao() {
        int largura = LARGURA_SPRITE * ESCALA_SPRITE;
        int altura = ALTURA_SPRITE * ESCALA_SPRITE;
        int offsetX = viradoParaEsquerda ?  (largura - 40) / 2: (largura - 20) / 2;
        int offsetY = (altura - 20) / 2;
        return new Rectangle(posicao.x + offsetX, posicao.y + offsetY, 30, 30);
    }
}
