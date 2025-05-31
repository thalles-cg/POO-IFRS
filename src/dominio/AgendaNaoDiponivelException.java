package dominio;

public class AgendaNaoDiponivelException extends RuntimeException {
    public AgendaNaoDiponivelException(String message) {
        super(message);
    }
}
