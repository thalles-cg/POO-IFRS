package dominio;

public enum Status {
    MARCADO("Marcado"),
    CONCLUIDO("Concluído"),
    NAO_REALIZADO("Não realizado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
