package dominio;

public enum Status {
    MARCADO("Marcado"),
    CONCLUIDO("Concluído");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
