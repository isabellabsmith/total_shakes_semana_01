package pedido;

import produto.Shake;

public class ItemPedido {
    private Shake shake;
    private int quantidade;

    public ItemPedido(Shake shake, int quantidade) {
        this.shake = shake;
        this.quantidade = quantidade;
    }
public enum TipoTamanho {
    //IMPLEMENTE A LOGICA DO ENUM
    P,M,G;
    //TODO
    public final double multiplicador(TipoTamanho tamanho) {
        if(tamanho == TipoTamanho.P) {
            return 1.0;
        } else if (tamanho == TipoTamanho.M) {
            return 1.30;
        } else {
            return 1.50;
        }
    }

}
    public Shake getShake() {
        return shake;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return this.shake + " / x" + this.quantidade;
    }
}
