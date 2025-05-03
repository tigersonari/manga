package br.manga.Resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.service.MangaService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MangaResourceTest {

    @Inject
    MangaService service;

    @Test
    @Order(1)
    void testFindAll() {
        given()
            .when().get("/mangas")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void testFindById() {
        MangaDTO manga = new MangaDTO("Manga Teste", "1234567890123", LocalDate.now(), 29.90, "Sinopse teste", 1, 1, 1, 1L, 1L);
        Long id = service.create(manga).id();

        given()
            .when().get("/mangas/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("titulo", is("Manga Teste"));
    }

    @Test
    @Order(3)
    void testFindByTitulo() {
        MangaDTO manga = new MangaDTO("Manga Titulo", "1234567890124", LocalDate.now(), 39.90, "Sinopse titulo", 1, 1, 1, 2L, 2L);
        service.create(manga);

        given()
            .when().get("/mangas/titulo/Manga Titulo")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Manga Titulo"));
    }

    @Test
    @Order(4)
    void testFindByGenero() {
        MangaDTO manga = new MangaDTO("Manga Genero", "1234567890125", LocalDate.now(), 49.90, "Sinopse genero", 1, 2, 1, 3L, 3L);
        service.create(manga);

        given()
            .when().get("/mangas/genero/2")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Manga Genero"));
    }

    @Test
    @Order(5)
    void testFindByEditora() {
        MangaDTO manga = new MangaDTO("Manga Editora", "1234567890126", LocalDate.now(), 59.90, "Sinopse editora", 1, 1, 1, 4L, 4L);
        service.create(manga);

        given()
            .when().get("/mangas/editora/4")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Manga Editora"));
    }

    @Test
    @Order(6)
    void testFindByIsbn() {
        MangaDTO manga = new MangaDTO("Manga ISBN", "1234567890127", LocalDate.now(), 69.90, "Sinopse isbn", 1, 1, 1, 5L, 5L);
        service.create(manga);

        given()
            .when().get("/mangas/isbn/1234567890127")
            .then()
                .statusCode(200)
                .body("titulo", is("Manga ISBN"));
    }

    @Test
    @Order(7)
    void testCreate() {
        MangaDTO manga = new MangaDTO("Manga Novo", "1234567890128", LocalDate.now(), 79.90, "Sinopse nova", 1, 1, 1, 6L, 6L);

        given()
            .contentType(ContentType.JSON)
            .body(manga)
            .when().post("/mangas")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("titulo", is("Manga Novo"))
                .body("isbn", is("1234567890128"));
    }

    static Long id = null;

    @Test
    @Order(8)
    void testUpdate() {
        MangaDTO manga = new MangaDTO("Manga Original", "1234567890129", LocalDate.now(), 89.90, "Sinopse original", 1, 1, 1, 7L, 7L);
        id = service.create(manga).id();

        MangaDTO updated = new MangaDTO("Manga Atualizado", "1234567890130", LocalDate.now(), 99.90, "Sinopse atualizada", 1, 1, 1, 7L, 7L);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/mangas/" + id)
            .then()
                .statusCode(204);

        MangaResponseDTO response = service.findById(id);
        assertThat(response.titulo(), is("Manga Atualizado"));
        assertThat(response.isbn(), is("1234567890130"));
    }

    @Test
    @Order(9)
    void testDelete() {
        MangaDTO manga = new MangaDTO("Manga Deletar", "1234567890131", LocalDate.now(), 109.90, "Sinopse deletar", 1, 1, 1, 8L, 8L);
        Long idDeletar = service.create(manga).id();

        given()
            .when().delete("/mangas/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            MangaResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Manga não encontrado"));
        }
    }
}