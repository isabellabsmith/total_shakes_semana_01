package pedido;

import ingredientes.Adicional;
import ingredientes.Base;
import produto.TipoTamanho;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return itens.stream()
                .map(itemPedido -> {
                    final var shake = itemPedido.getShake();
                    final var adicionais = shake.getAdicionais();
                    final var precoBase = cardapio.buscarPreco(shake.getBase()) * shake.getTipoTamanho().multiplicador;
                    final var precoAdicionais = adicionais.stream().map(cardapio::buscarPreco).reduce(0.0, Double::sum);
                    return ((precoBase + precoAdicionais) * itemPedido.getQuantidade());
                })
                .reduce(0.0, Double::sum);
    }

    int quantidade = 0;
    int index = 0;

    public boolean existeItem(ItemPedido itemPedido) {
        for(ItemPedido item: itens) {
            if (item.getShake().toString().equals(itemPedido.getShake().toString())) {
                quantidade = item.getQuantidade();
                index = itens.indexOf(item);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){
        if(existeItem(itemPedidoAdicionado)) {
            itemPedidoAdicionado.setQuantidade(quantidade + itemPedidoAdicionado.getQuantidade());
            itens.set(index, itemPedidoAdicionado);
        } else {
            itens.add(itemPedidoAdicionado);
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        if(existeItem(itemPedidoRemovido)) {
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
