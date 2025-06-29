package model;

import java.util.Random;

public class Inimigo extends Personagem {
    private Random random = new Random();

    public Inimigo(int x, int y, int velocidade) {
        super(x, y, velocidade);
    }

    @Override
    public void atualizar() {
        moverAleatorio();
    }

    public void moverAleatorio() {
        int direcao = random.nextInt(4);
        mover(direcao);
    }
}
