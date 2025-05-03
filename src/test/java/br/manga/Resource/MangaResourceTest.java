package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.manga.dto.MangaDTO;
import br.manga.dto.MangaResponseDTO;
import br.manga.model.Autor;
import br.manga.model.Editora;
import br.manga.service.MangaService;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MangaResourceTest {

    @Inject
    MangaService service;

    private Long[] createValidEntities() {
        Autor autor = new Autor();
        autor.setNome("Test Autor");
        autor.setNacionalidade("Brasileiro");
        Panache.getEntityManager().persist(autor);

        Editora editora = new Editora();
        editora.setNome("Test Editora");
        editora.setSede("São Paulo");
        editora.setFundacao(LocalDate.of(2000, 1, 1));
        Panache.getEntityManager().persist(editora);

        return new Long[]{autor.getId(), editora.getId()};
    }

    @Test
    @Order(1)
    @io.quarkus.test.TestTransaction
    void testFindAll() {
        given()
            .when().get("/mangas")
            .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    @io.quarkus.test.TestTransaction
    void testFindById() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga Teste", "1234567890123", LocalDate.now(), 29.90, "Sinopse teste", 1, 1, 1, ids[0], ids[1]);
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
    @io.quarkus.test.TestTransaction
    void testFindByTitulo() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga Titulo", "1234567890124", LocalDate.now(), 39.90, "Sinopse titulo", 1, 1, 1, ids[0], ids[1]);
        service.create(manga);

        given()
            .when().get("/mangas/titulo/Manga Titulo")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Manga Titulo"));
    }

    @Test
    @Order(4)
    @io.quarkus.test.TestTransaction
    void testFindByGenero() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga Genero", "1234567890125", LocalDate.now(), 49.90, "Sinopse genero", 1, 1, 1, ids[0], ids[1]);
        service.create(manga);

        given()
            .when().get("/mangas/genero/1")
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Manga Genero"));
    }

    @Test
    @Order(5)
    @io.quarkus.test.TestTransaction
    void testFindByEditora() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga Editora", "1234567890126", LocalDate.now(), 59.90, "Sinopse editora", 1, 1, 1, ids[0], ids[1]);
        service.create(manga);

        given()
            .when().get("/mangas/editora/" + ids[1])
            .then()
                .statusCode(200)
                .body("[0].titulo", is("Manga Editora"));
    }

    @Test
    @Order(6)
    @io.quarkus.test.TestTransaction
    void testFindByIsbn() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga ISBN", "1234567890127", LocalDate.now(), 69.90, "Sinopse isbn", 1, 1, 1, ids[0], ids[1]);
        service.create(manga);

        given()
            .when().get("/mangas/isbn/1234567890127")
            .then()
                .statusCode(200)
                .body("titulo", is("Manga ISBN"));
    }

    @Test
    @Order(7)
    @io.quarkus.test.TestTransaction
    void testCreate() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga Novo", "1234567890128", LocalDate.now(), 79.90, "Sinopse nova", 1, 1, 1, ids[0], ids[1]);

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

    @Test
    @Order(8)
    @io.quarkus.test.TestTransaction
    void testUpdate() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga Original", "1234567890129", LocalDate.now(), 89.90, "Sinopse original", 1, 1, 1, ids[0], ids[1]);
        Long id = service.create(manga).id();

        MangaDTO updated = new MangaDTO("Manga Atualizado", "1234567890130", LocalDate.now(), 99.90, "Sinopse atualizada", 1, 1, 1, ids[0], ids[1]);

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
    @io.quarkus.test.TestTransaction
    void testDelete() {
        Long[] ids = createValidEntities();
        MangaDTO manga = new MangaDTO("Manga Deletar", "1234567890131", LocalDate.now(), 109.90, "Sinopse deletar", 1, 1, 1, ids[0], ids[1]);
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