package exceptions;

public class IngredienteNaoEncontrado extends IllegalArgumentException {
    private static final String MSG = "Ingrediente não encontrado.";

    public IngredienteNaoEncontrado() {
        super(MSG);
    }
}
