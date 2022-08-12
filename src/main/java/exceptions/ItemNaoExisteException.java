package exceptions;

public class ItemNaoExisteException extends IllegalArgumentException {
    private static final String MSG = "Item nao existe no pedido.";

    public ItemNaoExisteException() {
        super(MSG);
    }
}
