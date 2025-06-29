package model;

public enum Direcao {
    CIMA(0), BAIXO(1), ESQUERDA(2), DIREITA(3);

    private final int valor;

    Direcao(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    public static Direcao fromValor(int valor) {
        for (Direcao d : values()) {
            if (d.valor == valor) return d;
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}
