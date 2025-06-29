package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleTeclado implements KeyListener {
    private boolean cima = false;
    private boolean baixo = false;
    private boolean esquerda = false;
    private boolean direita = false;

    public boolean isCima() { return cima; }
    public boolean isBaixo() { return baixo; }
    public boolean isEsquerda() { return esquerda; }
    public boolean isDireita() { return direita; }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') cima = true;
        if (e.getKeyChar() == 's') baixo = true;
        if (e.getKeyChar() == 'a') esquerda = true;
        if (e.getKeyChar() == 'd') direita = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') cima = false;
        if (e.getKeyChar() == 's') baixo = false;
        if (e.getKeyChar() == 'a') esquerda = false;
        if (e.getKeyChar() == 'd') direita = false;
    }
}
