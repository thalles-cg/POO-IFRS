package model;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage[] frames;
    private int frameAtual;
    private int delay;
    private int contador;

        public SpriteSheet(BufferedImage sheet, int frameLargura, int frameAltura, int totalFrames, int delay) {
        frames = new BufferedImage[totalFrames];
        for (int i = 0; i < totalFrames; i++) {
            frames[i] = sheet.getSubimage(i * frameLargura, 0, frameLargura, frameAltura);
        }
        this.delay = delay;
        this.contador = 0;
        this.frameAtual = 0;
    }

    public void atualizar() {
        contador++;
        if (contador >= delay) {
            contador = 0;
            frameAtual = (frameAtual + 1) % frames.length;
        }
    }

    public BufferedImage getFrameAtual() {
        return frames[frameAtual];
    }

    public void resetar() {
        frameAtual = 0;
        contador = 0;
    }


}

