package armazem;

import ingredientes.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmazemTest {
    static Armazem armazem;
    static Fruta fruta;
    static Base base;
    static Topping topping;

    @BeforeAll
    static void beforeAll() {
        fruta = new Fruta(TipoFruta.MORANGO);
        base = new Base(TipoBase.IOGURTE);
        topping = new Topping(TipoTopping.MEL);
    }

    @BeforeEach
    void setup() {
        armazem = new Armazem();
    }

    @DisplayName("Cadastrar ingrediente corretamente")
    @Test
    void teste_cadastrar_ingrediente_armazem_properly() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.cadastrarIngredienteEmEstoque(base);
        armazem.cadastrarIngredienteEmEstoque(topping);

        assertAll(
                () -> assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta)),
                () -> assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(base)),
                () -> assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(topping))
        );
    }

    @DisplayName("Cadastrar ingrediente já cadastrado")
    @Test
    void teste_cadastrar_ingrediente_armazem_exception_ingredienteJaCadastrado() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            armazem.cadastrarIngredienteEmEstoque(base);
            armazem.cadastrarIngredienteEmEstoque(base);
        });

        assertEquals("Ingrediente já cadastrado.", excecao.getMessage());
    }

    @DisplayName("Descadastrar ingrediente corretamente")
    @Test
    void teste_descadastrar_ingrediente_armazem_properly() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.descadastrarIngredienteEmEstoque(fruta);

        assertFalse(armazem.existeIngrediente(fruta));
    }

    @DisplayName("Descadastrar ingrediente inexistente")
    @Test
    void teste_descadastrar_ingrediente_exception_ingredienteNaoEncontrado() {
        armazem.cadastrarIngredienteEmEstoque(base);
        armazem.descadastrarIngredienteEmEstoque(base);

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            armazem.consultarQuantidadeDoIngredienteEmEstoque(base);
        });

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }

    @DisplayName("Adicionar quantidade de ingrediente corretamente")
    @Test
    void teste_adicionar_quantidade_ingrediente_properly() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 3);

        assertEquals(3, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
    }

    @DisplayName("Adicionar quantidade de ingrediente para ingrediente inexistente")
    @Test
    void teste_adicionar_quantidade_exception_ingredienteNaoEncontrado() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                armazem.adicionarQuantidadeDoIngredienteEmEstoque(topping, 5));

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }

    @DisplayName("Adicionar quantidade negativa de ingrediente")
    @Test
    void teste_adicionar_quantidade_igualOuMenorAZero() {
        armazem.cadastrarIngredienteEmEstoque(base);
        Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                armazem.adicionarQuantidadeDoIngredienteEmEstoque(base, -10));

        assertEquals("Quantidade inválida.", excecao.getMessage());
    }

    @DisplayName("Reduzir quantidade de ingrediente corretamente")
    @Test
    void teste_reduzir_quantidade_ingrediente_properly() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 3);
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 1);

        assertEquals(2, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
    }

    @DisplayName("Reduzir quantidade de ingrediente maior que a existente em Armazem")
    @Test
    void teste_reduzir_quantidade_ingrediente_quantidadeIgualA1() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 1);

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 4);
        });

        assertEquals("Quantidade inválida.", excecao.getMessage());
    }

    @DisplayName("Reduzir quantidade de ingrediente inexistente")
    @Test
    void teste_reduzir_quantidade_exception_ingredienteNaoEncontrado() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(topping, 2));

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }

    @DisplayName("Reduzir quantidade negativa de ingrediente")
    @Test
    void teste_reduzir_quantidade_igualOuMenorAZero() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, -10));

        assertEquals("Quantidade inválida.", excecao.getMessage());
    }

    @DisplayName("Consultar quantidade de ingrediente corretamente")
    @Test
    void teste_consultar_quantidade_ingrediente_properly() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 5);

        armazem.cadastrarIngredienteEmEstoque(topping);

        assertEquals(5, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
        assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(topping));
    }

    @DisplayName("Consultar quantidade de ingrediente inexistente")
    @Test
    void teste_encontrar_quantidade_exception_ingredienteNaoEncontrado() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.consultarQuantidadeDoIngredienteEmEstoque(base));

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }
}