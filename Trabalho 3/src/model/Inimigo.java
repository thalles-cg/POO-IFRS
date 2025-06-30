package model;

import java.util.Random;

public class Inimigo extends Personagem {
    private final Random random = new Random();
    private long ultimoMovimento = 0;
    private final int intervaloMovimento = 300; // em milissegundos

    public Inimigo(int x, int y, int velocidade) {
        super(x, y, velocidade);
    }

    @Override
    public void atualizar() {
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
}
