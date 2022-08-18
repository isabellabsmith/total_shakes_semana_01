package armazem;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {
    Armazem() {}
    TreeMap<Ingrediente, Integer> estoque = new TreeMap<>();

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        if(existeIngrediente(ingrediente)) {
            throw new IllegalArgumentException("Ingrediente já cadastrado.");
        }
        estoque.put(ingrediente, 0);
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        if(existeIngrediente(ingrediente)) {
            estoque.remove(ingrediente);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado.");
        }
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        if(existeIngrediente(ingrediente) && estoque.get(ingrediente) != null) {
            return estoque.get(ingrediente);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado.");
        }
    }

    public boolean existeIngrediente(Ingrediente ingrediente) {
        return estoque.containsKey(ingrediente);
    }
}