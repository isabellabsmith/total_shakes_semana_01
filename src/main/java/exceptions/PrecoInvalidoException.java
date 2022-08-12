package exceptions;

public class PrecoInvalidoException extends IllegalArgumentException {
    private static final String MSG = "Preco invalido.";

    public PrecoInvalidoException() {
        super(MSG);
    }
}
