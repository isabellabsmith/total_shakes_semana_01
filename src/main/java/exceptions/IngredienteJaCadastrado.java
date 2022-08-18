package exceptions;

public class IngredienteJaCadastrado extends IllegalArgumentException {
    private static final String MSG = "Ingrediente já cadastrado.";

    public IngredienteJaCadastrado() {
        super(MSG);
    }
}
