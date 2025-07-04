package model;

public enum Direcao {
    CIMA(0, -1),
    BAIXO(0, 1),
    ESQUERDA(-1, 0),
    DIREITA(1, 0),
    CIMA_ESQUERDA(-1, -1),
    CIMA_DIREITA(1, -1),
    BAIXO_ESQUERDA(-1, 1),
    BAIXO_DIREITA(1, 1);

    private final int dx, dy;

    Direcao(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int dx() { return dx; }
    public int dy() { return dy; }

    public static Direcao fromValor(int val) {
        return values()[val % values().length];
    }
}

