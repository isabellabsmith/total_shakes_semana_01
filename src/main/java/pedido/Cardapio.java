package pedido;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Cardapio {
    private TreeMap<Ingrediente,Double> precos;

    public Cardapio(){
        this.precos= new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos(){
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente,Double preco){
        if(preco > 0) {
            precos.put(ingrediente, preco);
        }  else {
            throw new IllegalArgumentException("Preco invalido.");
        }
    }

    public boolean atualizarIngrediente(Ingrediente ingrediente,Double preco){
        if(preco > 0) {
            if(precos.containsKey(ingrediente)) {
                precos.replace(ingrediente, preco);
            }  else {
                throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
            }
        } else {
            throw new IllegalArgumentException("Preco invalido.");
        }

        return true;
    }

    public boolean removerIngrediente(Ingrediente ingrediente){
        if(precos.containsKey(ingrediente)) {
            precos.remove(ingrediente);
        }  else {
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }
        return true;
    }

    public Double buscarPreco(Ingrediente ingrediente){
        if(precos.containsKey(ingrediente)) {
            return precos.get(ingrediente);
        }  else {
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
