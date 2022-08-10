package pedido;

import ingredientes.Adicional;
import ingredientes.Base;
import produto.TipoTamanho;

import java.util.ArrayList;
import java.util.List;

public class Pedido{

    private int id;
    private  ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public double calcularTotal(Cardapio cardapio){
        double total = 0;

        for (ItemPedido itemPedido : itens){
            Base base = itemPedido.getShake().getBase();
            Double preco = cardapio.buscarPreco(base);
            TipoTamanho tamanho = itemPedido.getShake().getTipoTamanho();

            if(tamanho == TipoTamanho.P) {
                total += preco * itemPedido.getQuantidade();
            } else if (tamanho == TipoTamanho.M) {
                total += preco * 1.3 * itemPedido.getQuantidade();
            } else {
                total += preco * 1.5 * itemPedido.getQuantidade();
            }
        }

        total += this.calcularAdicionais(cardapio);
        return total;
    }

    private double calcularAdicionais(Cardapio cardapio) {
        double precoTotalAdicionais = 0;
        for(ItemPedido itemPedido : itens ) {
            double precoAdicionais = 0;
            List<Adicional> adicionais = itemPedido.getShake().getAdicionais();

            for(Adicional adicional : adicionais) {
                precoAdicionais += cardapio.buscarPreco(adicional);
            }

            precoTotalAdicionais += precoAdicionais * itemPedido.getQuantidade();
        }

        return precoTotalAdicionais;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){
        boolean existe = false;
        int quantidade = 0;
        int index = 0;
        for(ItemPedido item: itens) {
            if (item.getShake().toString().equals(itemPedidoAdicionado.getShake().toString())) {
                existe = true;
                quantidade = item.getQuantidade();
                index = itens.indexOf(item);
                break;
            }
        }
        if(existe) {
            itemPedidoAdicionado.setQuantidade(quantidade + itemPedidoAdicionado.getQuantidade());
            itens.set(index, itemPedidoAdicionado);
        } else {
            itens.add(itemPedidoAdicionado);
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        boolean existe = false;
        int quantidade = 0;
        int index = 0;
        for(ItemPedido item: itens) {
            if (item.getShake().toString().equals(itemPedidoRemovido.getShake().toString())) {
                existe = true;
                quantidade = item.getQuantidade();
                index = itens.indexOf(item);
                break;
            }
        }
        if(existe) {
            itemPedidoRemovido.setQuantidade(quantidade - 1);
            itens.set(index, itemPedidoRemovido);
            if(quantidade - 1 <= 0) {
                itens.remove(itemPedidoRemovido);
            }
        } else {
            throw new IllegalArgumentException("Item nao existe no pedido.");
        }
        return false;
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
