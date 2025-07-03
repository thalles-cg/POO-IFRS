package model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Animador {
    private Map<EstadoAnimacao, SpriteSheet> animacoes = new HashMap<>();
    private EstadoAnimacao estadoAtual;

    public void adicionarAnimacao(EstadoAnimacao estado, SpriteSheet spriteSheet) {
        animacoes.put(estado, spriteSheet);
    }

    public void setEstado(EstadoAnimacao novoEstado) {
        if (estadoAtual != novoEstado) {
            estadoAtual = novoEstado;
            animacoes.get(estadoAtual).resetar();
        }
    }

    public void atualizar() {
        animacoes.get(estadoAtual).atualizar();
    }

    public BufferedImage getFrameAtual() {
        return animacoes.get(estadoAtual).getFrameAtual();
    }
}
