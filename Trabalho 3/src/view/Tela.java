package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import controller.ControleTeclado;
import model.Jogador;

public class Tela extends JPanel implements Runnable {

    private Jogador jogador = new Jogador(300, 300);
    private ControleTeclado controle = new ControleTeclado();

    public ControleTeclado getControle() {
        return controle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (controle.isCima()) jogador.moverCima();
        if (controle.isBaixo()) jogador.moverBaixo();
        if (controle.isEsquerda()) jogador.moverEsquerda();
        if (controle.isDireita()) jogador.moverDireita();

        g.setColor(Color.BLUE);
        g.fillRect(jogador.getPosicao().x, jogador.getPosicao().y, 20, 20);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
