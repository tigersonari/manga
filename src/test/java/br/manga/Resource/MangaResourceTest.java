//OK

package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
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

    static Long id;

    @Test
    void testCreate() {
        MangaDTO dto = new MangaDTO("Manga Teste", "1234567890123", LocalDate.now(), 9.99, "Shounen", 1, 1, 1, 1L, 1L);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/mangas")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "titulo", is("Manga Teste"));
    }

    @Test
    void testGetAll() {
        given()
            .when().get("/mangas")
            .then()
                .statusCode(200);  
    }

    @Test
    void testGetById() {
        given()
            .when().get("/mangas/1")
            .then()
                .statusCode(200)
                .body("titulo", is("Naruto"));
    }

    @Test
    void testGetByTitulo() {
        given()
            .when().get("/mangas/titulo/Naruto")
            .then()
                .statusCode(200);
    }


    @Test
    void testGetByEditora() {
        given()
            .when().get("/mangas/editora/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByIsbn() {
        given()
            .when().get("/mangas/isbn/9784088732735")
            .then()
                .statusCode(200);
    }

    @Test
    void testUpdate() {
        MangaDTO dto = new MangaDTO("Manga Teste", "3847563849576", LocalDate.now(), 9.99, "Shounen", 1, 1, 1, 1L, 1L);
        id = service.create(dto).id();

        MangaDTO updated = new MangaDTO("Manga Atualizado", "1908763405968", LocalDate.now(), 9.99, "Seinen", 1, 1, 1, 1L, 1L);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/mangas/" + id)
            .then()
                .statusCode(204);

        MangaResponseDTO response = service.findById(id);
        assertThat(response.titulo(), is("Manga Atualizado"));
    }

    @Test
    void testDelete() {
        MangaDTO dto = new MangaDTO("Manga Teste", "7375840384756", LocalDate.now(), 9.99, "Shounen", 1, 1, 1, 1L, 1L);
        id = service.create(dto).id();

        given()
            .when().delete("/mangas/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/mangas/" + id)
            .then()
                .statusCode(404);
    }
}
