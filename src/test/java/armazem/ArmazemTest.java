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
    void Should_ConsultQuantityOfIngredient_When_NewIngredientIsAddedProperly() {
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
    void ShouldNot_BeAbleToAddIngredient_When_AlreadyExists() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            armazem.cadastrarIngredienteEmEstoque(base);
            armazem.cadastrarIngredienteEmEstoque(base);
        });

        assertEquals("Ingrediente já cadastrado.", excecao.getMessage());
    }

    @DisplayName("Descadastrar ingrediente corretamente")
    @Test
    void Should_BeAbleToRemoveIngredient_When_IngredientExists() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.descadastrarIngredienteEmEstoque(fruta);

        assertFalse(armazem.existeIngrediente(fruta));
    }

    @DisplayName("Descadastrar ingrediente inexistente")
    @Test
    void ShouldNot_RemoveIngredient_When_IngredientDoesNotExist() {
        armazem.cadastrarIngredienteEmEstoque(base);
        armazem.descadastrarIngredienteEmEstoque(base);

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            armazem.consultarQuantidadeDoIngredienteEmEstoque(base);
        });

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }

    @DisplayName("Adicionar quantidade de ingrediente corretamente")
    @Test
    void Should_AddQuantityOfIngredient_When_IngredientExists() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 3);

        assertEquals(3, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
    }

    @DisplayName("Adicionar quantidade de ingrediente para ingrediente inexistente")
    @Test
    void ShouldNot_AddQuantityOfIngredient_When_IngredientDoesNotExist() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                armazem.adicionarQuantidadeDoIngredienteEmEstoque(topping, 5));

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }

    @DisplayName("Adicionar quantidade negativa de ingrediente")
    @Test
    void ShouldNot_AddQuantityOfIngredient_WhenQuantityEqualsOrBelowZero() {
        armazem.cadastrarIngredienteEmEstoque(base);
        Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                armazem.adicionarQuantidadeDoIngredienteEmEstoque(base, -10));

        assertEquals("Quantidade inválida.", excecao.getMessage());
    }

    @DisplayName("Reduzir quantidade de ingrediente corretamente")
    @Test
    void Should_ReduceQuantityOfIngredient_When_IngredientExists() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 3);
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 1);

        assertEquals(2, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
    }

    @DisplayName("Reduzir quantidade de ingrediente maior que a existente em Armazem")
    @Test
    void ShouldNot_ReduceQuantityOfIngredient_When_QuantityToRemoveIsBiggerThanCurrent() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 1);

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 4);
        });

        assertEquals("Quantidade inválida.", excecao.getMessage());
    }

    @DisplayName("Reduzir quantidade de ingrediente inexistente")
    @Test
    void ShouldNot_ReduceQuantityOfIngredient_When_IngredientDoesNotExist() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(topping, 2));

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }

    @DisplayName("Reduzir quantidade negativa de ingrediente")
    @Test
    void ShouldNot_ReduceQuantityOfIngredient_When_InputQuantityBelowZero() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, -10));

        assertEquals("Quantidade inválida.", excecao.getMessage());
    }

    @DisplayName("Consultar quantidade de ingrediente corretamente")
    @Test
    void Should_ConsultQuantityOfIngredient_When_IngredientExists() {
        armazem.cadastrarIngredienteEmEstoque(fruta);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 5);

        armazem.cadastrarIngredienteEmEstoque(topping);

        assertEquals(5, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
        assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(topping));
    }

    @DisplayName("Consultar quantidade de ingrediente inexistente")
    @Test
    void ShouldNot_ConsultQuantityOfIngredient_When_IngredientDoesNotExist() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.consultarQuantidadeDoIngredienteEmEstoque(base));

        assertEquals("Ingrediente não encontrado.", excecao.getMessage());
    }
}