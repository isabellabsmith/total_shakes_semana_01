package exceptions;

public class IngredienteInexistenteException extends IllegalArgumentException {
    private static final String MSG = "Ingrediente nao existe no cardapio.";

    public IngredienteInexistenteException() {
        super(MSG);
    }
}
