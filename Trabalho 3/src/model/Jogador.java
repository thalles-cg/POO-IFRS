package model;

import java.awt.image.BufferedImage;

public class Jogador extends Personagem implements Animavel {
    private Animador animador;
    private boolean movendo;
    private boolean viradoParaEsquerda = false;

    public Jogador(int x, int y, int velocidade, BufferedImage paradoSheet, BufferedImage andandoSheet) {
        super(x, y, velocidade);

        animador = new Animador();
        animador.adicionarAnimacao(EstadoAnimacao.PARADO, new SpriteSheet(paradoSheet, 50, 50, 10, 5));
        animador.adicionarAnimacao(EstadoAnimacao.ANDANDO, new SpriteSheet(andandoSheet, 50, 50, 8, 5));
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
}