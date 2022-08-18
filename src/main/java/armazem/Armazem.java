package armazem;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {
    public Armazem(){}

    TreeMap<Ingrediente, Integer> estoque = new TreeMap<>();

    Ingrediente ingrediente;

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {}

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {}

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {}

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {}

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) { return 1; }
}
