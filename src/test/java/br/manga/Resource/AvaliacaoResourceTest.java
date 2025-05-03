package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.AvaliacaoDTO;
import br.manga.dto.AvaliacaoResponseDTO;
import br.manga.model.Manga;
import br.manga.service.AvaliacaoService;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class AvaliacaoResourceTest {

    @Inject
    AvaliacaoService service;

    private Long createValidManga() {
        Manga manga = new Manga();
        manga.setTitulo("Test Manga");
        manga.setIsbn("1234567890123");
        manga.setLancamento(LocalDate.now());
        manga.setPreco(29.90);
        manga.setSinopse("Teste");
        manga.setEstoque(br.manga.model.Estoque.DISPONIVEL);
        manga.setGenero(br.manga.model.Genero.SHOUNEN);
        manga.setClassificacao(br.manga.model.Classificacao.LIVRE);
        Panache.getEntityManager().persist(manga);
        return manga.getId();
    }

    @Test
    @Order(1)
    @io.quarkus.test.TestTransaction
    void testFindAll() {
        given()
            .when().get("/avaliacoes")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    @io.quarkus.test.TestTransaction
    void testFindById() {
        Long mangaId = createValidManga();
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(8.5, "Ótimo mangá!", mangaId);
        Long id = service.create(avaliacao).id();

        given()
            .when().get("/avaliacoes/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nota", is(8.5f));
    }

    @Test
    @Order(3)
    @io.quarkus.test.TestTransaction
    void testFindByManga() {
        Long mangaId = createValidManga();
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(9.0, "Fantástico!", mangaId);
        service.create(avaliacao);

        given()
            .when().get("/avaliacoes/manga/" + mangaId)
            .then()
                .statusCode(200)
                .body("[0].nota", is(9.0f));
    }

    @Test
    @Order(4)
    @io.quarkus.test.TestTransaction
    void testFindByNotaGreaterThanEqual() {
        Long mangaId = createValidManga();
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(7.5, "Bom!", mangaId);
        service.create(avaliacao);

        given()
            .when().get("/avaliacoes/nota-minima/7.0")
            .then()
                .statusCode(200)
                .body("[0].nota", is(7.5f));
    }

    @Test
    @Order(5)
    @io.quarkus.test.TestTransaction
    void testCalcularMediaNotas() {
        Long mangaId = createValidManga();
        AvaliacaoDTO avaliacao1 = new AvaliacaoDTO(8.0, "Muito bom!", mangaId);
        service.create(avaliacao1);

        AvaliacaoDTO avaliacao2 = new AvaliacaoDTO(9.0, "Excelente!", mangaId);
        service.create(avaliacao2);

        given()
            .when().get("/avaliacoes/media/" + mangaId)
            .then()
                .statusCode(200)
                .body(is("8.5"));
    }

    @Test
    @Order(6)
    @io.quarkus.test.TestTransaction
    void testCreate() {
        Long mangaId = createValidManga();
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(8.8, "Adorei!", mangaId);

        given()
            .contentType(ContentType.JSON)
            .body(avaliacao)
            .when().post("/avaliacoes")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nota", is(8.8f))
                .body("comentario", is("Adorei!"));
    }

    @Test
    @Order(7)
    @io.quarkus.test.TestTransaction
    void testUpdate() {
        Long mangaId = createValidManga();
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(7.0, "Regular", mangaId);
        Long id = service.create(avaliacao).id();

        AvaliacaoDTO updated = new AvaliacaoDTO(9.5, "Muito melhor!", mangaId);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/avaliacoes/" + id)
            .then()
                .statusCode(204);

        AvaliacaoResponseDTO response = service.findById(id);
        assertThat(response.nota(), is(9.5));
        assertThat(response.comentario(), is("Muito melhor!"));
    }

    @Test
    @Order(8)
    @io.quarkus.test.TestTransaction
    void testDelete() {
        Long mangaId = createValidManga();
        AvaliacaoDTO avaliacao = new AvaliacaoDTO(6.5, "Para deletar", mangaId);
        Long idDeletar = service.create(avaliacao).id();

        given()
            .when().delete("/avaliacoes/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            AvaliacaoResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Avaliação não encontrada"));
        }
    }
}