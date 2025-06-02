package dominio;

public class ExameNaoTerminadoException extends RuntimeException {
    public ExameNaoTerminadoException(String message) {
        super(message);
    }
}
