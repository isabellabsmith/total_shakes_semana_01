package armazem;

import exceptions.IngredienteJaCadastrado;
import exceptions.IngredienteNaoEncontrado;
import exceptions.QuantidadeInvalida;
import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {
    Armazem() {
    }

    TreeMap<Ingrediente, Integer> estoque = new TreeMap<>();

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) throws IngredienteJaCadastrado {
        if (existeIngrediente(ingrediente)) {
            throw new IngredienteJaCadastrado();
        }
        estoque.put(ingrediente, 0);
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        ingredienteEncontrado(ingrediente);
        estoque.remove(ingrediente);
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        ingredienteEncontrado(ingrediente);
        int quantidadeAtual = consultarQuantidadeDoIngredienteEmEstoque(ingrediente);
        verificarQuantidade(quantidade);
        estoque.put(ingrediente, quantidadeAtual + quantidade);
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) throws QuantidadeInvalida {
        ingredienteEncontrado(ingrediente);
        int quantidadeAtual = consultarQuantidadeDoIngredienteEmEstoque(ingrediente);
        verificarQuantidade(quantidade);
        if (quantidadeAtual > quantidade) {
            estoque.put(ingrediente, quantidadeAtual - quantidade);
        } else {
            throw new QuantidadeInvalida();
        }
    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        ingredienteEncontrado(ingrediente);
        return estoque.get(ingrediente);
    }

    public boolean existeIngrediente(Ingrediente ingrediente) {
        return estoque.containsKey(ingrediente);
    }

    public boolean ingredienteEncontrado(Ingrediente ingrediente) throws IngredienteNaoEncontrado {
        if (existeIngrediente(ingrediente)) {
            return true;
        } else {
            throw new IngredienteNaoEncontrado();
        }
    }

    private void verificarQuantidade(Integer quantidade) throws QuantidadeInvalida {
        if (quantidade < 0)
            throw new QuantidadeInvalida();
    }
}