package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.List;

import controller.ControleTeclado;

public class Fase extends JPanel implements Runnable {
    private Jogador jogador;
    private List<Inimigo> inimigos;
    private Objetivo objetivo;
    private int faseAtual = 1;
    private boolean gameOver = false;
    private boolean vitoria = false;
    private final ControleTeclado controle;
    private Image fundo;

    public Fase(ControleTeclado controle) {
        this.controle = controle;
        URL imgURL = getClass().getResource("/resources/img/grass.jpg");
        if (imgURL != null) {
            this.fundo = new ImageIcon(imgURL).getImage();
        }
        iniciar();
        new Thread(this).start();
    }

    private void desenharTextoCentralizado(Graphics g, String msg, Color cor, int tamanhoFonte) {
        try {
            InputStream is = getClass().getResourceAsStream("/resources/font/PressStart2P-Regular.ttf");
            Font arcade = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) tamanhoFonte);
            g.setFont(arcade);
        } catch (Exception e) {
            g.setFont(new Font("Arial", Font.BOLD, tamanhoFonte)); // fallback
        }

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(msg);
        int textHeight = fm.getHeight();

        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() - textHeight) / 2 + fm.getAscent();

        g.setColor(cor);
        g.drawString(msg, x, y);
    }

    public void iniciar() {
        if (faseAtual == 1) jogador = new Jogador(10, new Random().nextInt(500), faseAtual * 8);
        else jogador = new Jogador(10, new Random().nextInt(500), faseAtual + 8);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraTela = tela.width;
        int alturaTela = tela.height;

        objetivo = new Objetivo(larguraTela - 100, new Random().nextInt(alturaTela - 100) + 50);
        inimigos = new ArrayList<>();

        for (int i = 0; i < faseAtual * 4; i++) {
            inimigos.add(new Inimigo(new Random().nextInt(larguraTela - 200) + 100, new Random().nextInt(alturaTela), faseAtual*2));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
        if (gameOver) {
            desenharTextoCentralizado(g, "Game Over!", Color.RED, 36);
            return;
        } else if (vitoria) {
            desenharTextoCentralizado(g, "Fase Concluída!", Color.GREEN, 56);
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
        this.vitoria = false;
        while (true) {
            if (!gameOver && !vitoria) {
                if (controle.isCima()) jogador.mover(Direcao.CIMA);
                if (controle.isBaixo()) jogador.mover(Direcao.BAIXO);
                if (controle.isEsquerda()) jogador.mover(Direcao.ESQUERDA);
                if (controle.isDireita()) jogador.mover(Direcao.DIREITA);

                for (Inimigo inimigo : inimigos) {
                    inimigo.atualizar();
                }
                verificarColisoes();
                repaint();
            }
            if (vitoria) {
                repaint();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                faseAtual++;
                vitoria = false;
                iniciar();
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
