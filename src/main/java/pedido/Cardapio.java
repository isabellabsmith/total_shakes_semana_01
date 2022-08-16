package pedido;

import exceptions.IngredienteInexistenteException;
import exceptions.PrecoInvalidoException;
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

    private void validarPreco(final Double preco) throws PrecoInvalidoException {
        if(preco <=0) {
            throw new PrecoInvalidoException();
        }
    }

    private void validarIngrediente(final Ingrediente ingrediente) throws IngredienteInexistenteException {
        if(!precos.containsKey(ingrediente)) {
            throw new IngredienteInexistenteException();
        }
    }

    public void adicionarIngrediente(Ingrediente ingrediente,Double preco){
        validarPreco(preco);
        precos.put(ingrediente, preco);
    }

    public boolean atualizarIngrediente(Ingrediente ingrediente,Double preco){
        validarIngrediente(ingrediente);
        validarPreco(preco);
        precos.replace(ingrediente, preco);
        return true;
    }

    public boolean removerIngrediente(Ingrediente ingrediente){
        validarIngrediente(ingrediente);
        precos.remove(ingrediente);
        return true;
    }

    public Double buscarPreco(Ingrediente ingrediente){
        validarIngrediente(ingrediente);
        return precos.get(ingrediente);
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
