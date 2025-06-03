package dominio;

public class ExameNaoExiste extends RuntimeException {
    public ExameNaoExiste(String message) {
        super(message);
    }
}
