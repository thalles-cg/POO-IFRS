package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Inimigo extends Personagem implements Animavel {
    private Animador animador;
    private final Random random = new Random();
    private boolean viradoParaEsquerda = false;

    private final int LARGURA_SPRITE = 48;
    private final int ALTURA_SPRITE = 48;
    private final int ESCALA_SPRITE = 3;

    private Direcao direcaoHorizontal;
    private Direcao direcaoVertical;
    private long tempoDirecaoRestante = 0;
    private boolean moverHorizontal = true;
    private boolean moverVertical = true;

    public Inimigo(int x, int y, int velocidade, BufferedImage paradoSheet, BufferedImage andandoSheet) {
        super(x, y, velocidade);

        animador = new Animador();
        animador.adicionarAnimacao(EstadoAnimacao.PARADO, new SpriteSheet(paradoSheet, LARGURA_SPRITE, ALTURA_SPRITE, 4, 10));
        animador.adicionarAnimacao(EstadoAnimacao.ANDANDO, new SpriteSheet(andandoSheet, LARGURA_SPRITE, ALTURA_SPRITE, 6, 10));
        animador.setEstado(EstadoAnimacao.PARADO);

        sortearNovasDirecoes();
    }

    public void sortearNovasDirecoes() {
        moverHorizontal = random.nextBoolean(); // 50% chance de andar na horizontal
        moverVertical = random.nextBoolean();   // 50% chance de andar na vertical

        if (!moverHorizontal && !moverVertical) moverHorizontal = true; // garante que ele se mova

        if (moverHorizontal) {
            direcaoHorizontal = random.nextBoolean() ? Direcao.DIREITA : Direcao.ESQUERDA;
        }
        if (moverVertical) {
            direcaoVertical = random.nextBoolean() ? Direcao.CIMA : Direcao.BAIXO;
        }

        tempoDirecaoRestante = 800 + random.nextInt(1000); // 0.8 a 1.8 segundos
    }

    @Override
    public void atualizar() {
        atualizarAnimacao();

        if (tempoDirecaoRestante <= 0) {
            sortearNovasDirecoes();
        }

        boolean moveu = false;
        if (moverHorizontal) {
            mover(direcaoHorizontal);
            moveu = true;
        }
        if (moverVertical) {
            mover(direcaoVertical);
            moveu = true;
        }

        animador.setEstado(moveu ? EstadoAnimacao.ANDANDO : EstadoAnimacao.PARADO);
        tempoDirecaoRestante -= 20;
    }

    @Override
    public void mover(Direcao direcao) {
        super.mover(direcao);

        // só atualiza se for horizontal
        if (direcao == Direcao.ESQUERDA) viradoParaEsquerda = true;
        else if (direcao == Direcao.DIREITA) viradoParaEsquerda = false;
    }

    @Override
    public void atualizarAnimacao() {
        animador.atualizar();
    }

    @Override
    public BufferedImage getImagemAtual() {
        BufferedImage frameAtual = animador.getFrameAtual();

        if (!viradoParaEsquerda) {
            return frameAtual;
        }

        int largura = frameAtual.getWidth();
        int altura = frameAtual.getHeight();

        BufferedImage espelhado = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = espelhado.createGraphics();
        g2d.drawImage(frameAtual, largura, 0, -largura, altura, null);
        g2d.dispose();

        return espelhado;
    }




    public boolean isViradoParaEsquerda() {
        return viradoParaEsquerda;
    }

    public Rectangle getRetanguloColisao() {
        int larguraSprite = LARGURA_SPRITE * ESCALA_SPRITE;
        int larguraHitbox = 25 * ESCALA_SPRITE;
        int alturaHitbox = 30;
        int offsetY = 85;

        int offsetX = viradoParaEsquerda
                ? 20
                : larguraSprite - (20 + larguraHitbox);

        return new Rectangle(posicao.x + offsetX, posicao.y + offsetY, larguraHitbox, alturaHitbox);
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
