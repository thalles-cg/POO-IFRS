package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private final int ESCALA_SPRITE_JOGADOR = 3;

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
        BufferedImage parado = null;
        BufferedImage andando = null;
        try {
             parado = ImageIO.read(getClass().getResourceAsStream("/resources/spritesheet/jogador/Cat/Cat-2-Idle.png"));
             andando = ImageIO.read(getClass().getResourceAsStream("/resources/spritesheet/jogador/Cat/Cat-2-Run.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (faseAtual == 1) jogador = new Jogador(10, new Random().nextInt(500), faseAtual * 8, parado, andando);
        else jogador = new Jogador(10, new Random().nextInt(500), faseAtual + 8, parado, andando);

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraTela = tela.width;
        int alturaTela = tela.height;

        objetivo = new Objetivo(larguraTela - 100, new Random().nextInt(alturaTela - 100) + 50);
        inimigos = new ArrayList<>();

        for (int i = 0; i < faseAtual * 4; i++) {
            inimigos.add(new Inimigo(new Random().nextInt(larguraTela - 200) + 100, new Random().nextInt(alturaTela), faseAtual*2, andando));
        }
    }

    private void desenharSprite(Graphics g, BufferedImage imagem, int x, int y, int escala, boolean viradoParaEsquerda) {
        Graphics2D g2d = (Graphics2D) g;
        int largura = imagem.getWidth() * escala;
        int altura = imagem.getHeight() * escala;

        if (viradoParaEsquerda) {
            g2d.drawImage(imagem, x + largura, y, -largura, altura, null);
        } else {
            g2d.drawImage(imagem, x, y, largura, altura, null);
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

        BufferedImage img = jogador.getImagemAtual();
        int largura = img.getWidth() * ESCALA_SPRITE_JOGADOR;
        int altura = img.getHeight() * ESCALA_SPRITE_JOGADOR;

        desenharSprite(g, jogador.getImagemAtual(), jogador.getPosicao().x, jogador.getPosicao().y, ESCALA_SPRITE_JOGADOR, jogador.isViradoParaEsquerda());


        for (Inimigo inimigo : inimigos) {
            g.drawImage(inimigo.getImagemAtual(), inimigo.getPosicao().x, inimigo.getPosicao().y, null);
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

                jogador.atualizar();

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
