package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import org.junit.jupiter.api.Test;

import br.manga.dto.MangaDTO;
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
    void testCriarManga() {
        MangaDTO dto = new MangaDTO("Naruto", "Masashi Kishimoto", LocalDate.of(2000, 1, 1), 9.99, "Ação", 1, 1, 1, 1L, 1L);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/mangas")
            .then()
                .statusCode(201)
                .body("titulo", is("Naruto"));
    }

    @Test
    void testBuscarPorTitulo() {
        MangaDTO dto = new MangaDTO("Bleach", "Tite Kubo", LocalDate.of(2001, 1, 1), 9.99, "Ação", 1, 1, 1, 1L, 1L);
        service.create(dto);

        given()
            .when().get("/mangas/titulo/Bleach")
            .then()
                .statusCode(200)
                .body("titulo", equalTo("Bleach"));
    }

    @Test
    void testBuscarPorGenero() {
        given()
            .when().get("/mangas/genero/Ação")
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void testBuscarPorAutor() {
        given()
            .when().get("/mangas/autor/Masashi Kishimoto")
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void testAtualizarManga() {
        MangaDTO dto = new MangaDTO("One Piece", "Oda", LocalDate.of(1999, 1, 1), 9.99, "Aventura", 1, 1, 1, 1L, 1L);
        Long id = service.create(dto).id();

        MangaDTO atualizado = new MangaDTO("One Piece", "Eiichiro Oda", LocalDate.of(1999, 1, 1), 9.99, "Aventura", 1, 1, 1, 1L, 1L);

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/mangas/" + id)
            .then()
                .statusCode(204);
    }

    @Test
    void testDeletarManga() {
        MangaDTO dto = new MangaDTO("Death Note", "Tsugumi Ohba", LocalDate.of(2003, 1, 1), 9.99, "Suspense", 1, 1, 1, 1L, 1L);
        Long id = service.create(dto).id();

        given()
            .when().delete("/mangas/" + id)
            .then()
                .statusCode(204);
    }
}
