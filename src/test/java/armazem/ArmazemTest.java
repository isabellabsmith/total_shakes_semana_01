package armazem;

import ingredientes.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ArmazemTest {
    static Armazem armazem;
    static Fruta fruta;
    static Base base;
    static Topping topping;

    @BeforeAll
    static void beforeAll() {
        armazem = new Armazem();
        fruta = new Fruta(TipoFruta.MORANGO);
        base = new Base(TipoBase.IOGURTE);
        topping = new Topping(TipoTopping.MEL);
    }

    @Nested
    class CadastrarNovoIngredienteEmEstoque {
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

        @Test
        void  teste_cadastrar_ingrediente_armazem_exception_ingredienteJaCadastrado() {
            Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
                armazem.cadastrarIngredienteEmEstoque(base);
                armazem.cadastrarIngredienteEmEstoque(base);
            });

            assertEquals("Ingrediente já cadastrado", excecao.getMessage());
        }
    }

    @Nested
    class DescadastrarIngredienteEmEstoque {
        @Test
        void teste_descadastrar_ingrediente_armazem_properly() {
            armazem.cadastrarIngredienteEmEstoque(fruta);
            armazem.descadastrarIngredienteEmEstoque(fruta);

            assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
        }

        @Test
        void teste_descadastrar_ingrediente_exception_ingredienteNaoEncontrado() {
            armazem.cadastrarIngredienteEmEstoque(base);
            armazem.descadastrarIngredienteEmEstoque(base);

            Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
                armazem.consultarQuantidadeDoIngredienteEmEstoque(base);
            });

            assertEquals("Ingrediente não encontrado", excecao.getMessage());
            }
        }

    @Nested
    class AdicionarQuantidadeEmEstoque {
        @Test
        void teste_adicionar_quantidade_ingrediente_properly() {
            armazem.cadastrarIngredienteEmEstoque(fruta);
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 3);

            assertEquals(3, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
        }

        @Test
        void teste_adicionar_quantidade_exception_ingredienteNaoEncontrado() {
            Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                    armazem.adicionarQuantidadeDoIngredienteEmEstoque(topping, 5));

            assertEquals("Ingrediente não encontrado", excecao.getMessage());
        }

        @Test
        void teste_adicionar_quantidade_igualOuMenorAZero() {
            armazem.cadastrarIngredienteEmEstoque(base);
            Exception excecao = assertThrows(IllegalArgumentException.class, () ->
                    armazem.adicionarQuantidadeDoIngredienteEmEstoque(base, -10));

            assertEquals("Quantidade inválida", excecao.getMessage());
        }
    }

    @Nested
    class ReduzirQuantidade {
        @Test
        void teste_reduzir_quantidade_ingrediente_properly() {
            armazem.cadastrarIngredienteEmEstoque(fruta);
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 3);
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 1);

            assertEquals(2, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
        }

        @Test
        void teste_reduzir_quantidade_ingrediente_quantidadeIgualA1() {
            armazem.cadastrarIngredienteEmEstoque(fruta);
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 1);
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 1);

            assertEquals(0, armazem.getEstoque().size());
        }

        @Test
        void teste_reduzir_quantidade_exception_ingredienteNaoEncontrado() {
            Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(topping, 2));

            assertEquals("Ingrediente não encontrado", excecao.getMessage());
        }

        @Test
        void teste_reduzir_quantidade_igualOuMenorAZero() {
            armazem.cadastrarIngredienteEmEstoque(fruta);
            Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 2));

            assertEquals("Quantidade invalida", excecao.getMessage());
        }
    }

    @Nested
    class ConsultarQuantidade {
        @Test
        void teste_consultar_quantidade_ingrediente_properly() {
            armazem.cadastrarIngredienteEmEstoque(fruta);
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 5);

            armazem.cadastrarIngredienteEmEstoque(topping);

            assertEquals(5, armazem.consultarQuantidadeDoIngredienteEmEstoque(fruta));
            assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(topping));
        }

        @Test
        void teste_encontrar_quantidade_exception_ingredienteNaoEncontrado() {
            Exception excecao = assertThrows(IllegalArgumentException.class, () -> armazem.consultarQuantidadeDoIngredienteEmEstoque(base));

            assertEquals("Ingrediente não encontrado", excecao.getMessage());
        }
    }
}