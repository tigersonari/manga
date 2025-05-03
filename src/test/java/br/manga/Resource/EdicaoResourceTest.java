package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;
import br.manga.model.Manga;
import br.manga.service.EdicaoService;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EdicaoResourceTest {

    @Inject
    EdicaoService service;

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
            .when().get("/edicoes")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    @io.quarkus.test.TestTransaction
    void testFindById() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(1, "Português", LocalDate.now(), "15x21", "Edição Teste", 1, 1, 1, mangaId);
        Long id = service.create(edicao).id();

        given()
            .when().get("/edicoes/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("titulo", is("Edição Teste"));
    }

    @Test
    @Order(3)
    @io.quarkus.test.TestTransaction
    void testFindByManga() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(2, "Inglês", LocalDate.now(), "15x21", "Edição Manga", 1, 1, 1, mangaId);
        service.create(edicao);

        given()
            .when().get("/edicoes/manga/" + mangaId)
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Edição Manga"));
    }

    @Test
    @Order(4)
    @io.quarkus.test.TestTransaction
    void testFindByFormato() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(3, "Português", LocalDate.now(), "15x21", "Edição Formato", 1, 1, 1, mangaId);
        service.create(edicao);

        given()
            .when().get("/edicoes/formato/1")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Edição Formato"));
    }

    @Test
    @Order(5)
    @io.quarkus.test.TestTransaction
    void testFindByStatus() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(4, "Japonês", LocalDate.now(), "15x21", "Edição Status", 1, 1, 1, mangaId);
        service.create(edicao);

        given()
            .when().get("/edicoes/status/1")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Edição Status"));
    }

    @Test
    @Order(6)
    @io.quarkus.test.TestTransaction
    void testFindByVolumeAndManga() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(5, "Português", LocalDate.now(), "15x21", "Edição Volume", 1, 1, 1, mangaId);
        service.create(edicao);

        given()
            .when().get("/edicoes/volume/5/manga/" + mangaId)
            .then()
                .statusCode(200)
                .body("titulo", is("Edição Volume"));
    }

    @Test
    @Order(7)
    @io.quarkus.test.TestTransaction
    void testCreate() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(6, "Inglês", LocalDate.now(), "15x21", "Edição Nova", 1, 1, 1, mangaId);

        given()
            .contentType(ContentType.JSON)
            .body(edicao)
            .when().post("/edicoes")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("titulo", is("Edição Nova"))
                .body("volume", is(6));
    }

    @Test
    @Order(8)
    @io.quarkus.test.TestTransaction
    void testUpdate() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(7, "Português", LocalDate.now(), "15x21", "Edição Original", 1, 1, 1, mangaId);
        Long id = service.create(edicao).id();

        EdicaoDTO updated = new EdicaoDTO(7, "Inglês", LocalDate.now(), "15x21", "Edição Atualizada", 1, 1, 1, mangaId);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/edicoes/" + id)
            .then()
                .statusCode(204);

        EdicaoResponseDTO response = service.findById(id);
        assertThat(response.titulo(), is("Edição Atualizada"));
        assertThat(response.idioma(), is("Inglês"));
    }

    @Test
    @Order(9)
    @io.quarkus.test.TestTransaction
    void testDelete() {
        Long mangaId = createValidManga();
        EdicaoDTO edicao = new EdicaoDTO(8, "Japonês", LocalDate.now(), "15x21", "Edição Deletar", 1, 1, 1, mangaId);
        Long idDeletar = service.create(edicao).id();

        given()
            .when().delete("/edicoes/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            EdicaoResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Edição não encontrada"));
        }
    }
}