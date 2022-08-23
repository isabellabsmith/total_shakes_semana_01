package exceptions;

public class QuantidadeInvalida extends IllegalArgumentException {
    private static final String MSG = "Quantidade inválida.";

    public QuantidadeInvalida() {
        super(MSG);
    }
}
