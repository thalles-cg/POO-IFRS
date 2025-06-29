package view;

import model.Inimigo;
import model.Jogador;
import model.Objetivo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import controller.ControleTeclado;
import model.Personagem;

public class Fase extends JPanel implements Runnable {
    private Jogador jogador;
    private List<Inimigo> inimigos;
    private Objetivo objetivo;
    private int faseAtual = 1;
    private boolean gameOver = false;
    private boolean vitoria = false;
    private ControleTeclado controle;

    public Fase(ControleTeclado controle) {
        this.controle = controle;
        iniciar();
        new Thread(this).start();
    }

    public void iniciar() {
        jogador = new Jogador(50, 50, faseAtual * 5);
        objetivo = new Objetivo(300, 300);
        inimigos = new ArrayList<>();

        for (int i = 0; i < faseAtual * 2; i++) {
            inimigos.add(new Inimigo(100 + i * 30, 100, faseAtual));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over!", 150, 150);
            return;
        } else if (vitoria) {
            g.setColor(Color.GREEN);
            g.drawString("Fase Concluída!", 150, 150);
            return;
        }

        g.setColor(Color.BLUE);
        g.fillRect(jogador.getPosicao().x, jogador.getPosicao().y, 20, 20);

        g.setColor(Color.RED);
        for (Inimigo inimigo : inimigos) {
            g.fillRect(inimigo.getPosicao().x, inimigo.getPosicao().y, 20, 20);
        }

        g.setColor(Color.YELLOW);
        g.fillOval(objetivo.getPosicao().x, objetivo.getPosicao().y, 20, 20);
    }

    private void verificarColisoes() {
        Rectangle rJogador = new Rectangle(jogador.getPosicao().x, jogador.getPosicao().y, 20, 20);
        Rectangle rObjetivo = new Rectangle(objetivo.getPosicao().x, objetivo.getPosicao().y, 20, 20);

        if (rJogador.intersects(rObjetivo)) {
            vitoria = true;
            return;
        }

        for (Inimigo inimigo : inimigos) {
            Rectangle rInimigo = new Rectangle(inimigo.getPosicao().x, inimigo.getPosicao().y, 20, 20);
            if (rJogador.intersects(rInimigo)) {
                gameOver = true;
                return;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            if (!gameOver && !vitoria) {
                if (controle.isCima()) jogador.mover(Personagem.CIMA);
                if (controle.isBaixo()) jogador.mover(Personagem.BAIXO);
                if (controle.isEsquerda()) jogador.mover(Personagem.ESQUERDA);
                if (controle.isDireita()) jogador.mover(Personagem.DIREITA);

                for (Inimigo inimigo : inimigos) {
                    inimigo.atualizar();
                }
                verificarColisoes();
                repaint();
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Jogador getJogador() {
        return jogador;
    }
}
