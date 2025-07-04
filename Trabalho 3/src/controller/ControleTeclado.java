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
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) cima = true;
        if (code == KeyEvent.VK_S) baixo = true;
        if (code == KeyEvent.VK_A) esquerda = true;
        if (code == KeyEvent.VK_D) direita = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) cima = false;
        if (code == KeyEvent.VK_S) baixo = false;
        if (code == KeyEvent.VK_A) esquerda = false;
        if (code == KeyEvent.VK_D) direita = false;
    }
}
