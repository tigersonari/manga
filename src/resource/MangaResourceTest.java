package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    MangaService mangaService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/mangas")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        MangaDTO dto = new MangaDTO(
            "One Piece",
            "Eiichiro Oda", 
            java.time.LocalDate.of(1997, 7, 22), 
            99.90,
            "Adventure", 
            1, 
            1, 
            1, 
            123456789L, 
            987654321L  
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/mangas")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("titulo", is("One Piece"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/mangas/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testBuscarPorTitulo() {
        given()
            .when().get("/mangas/titulo?titulo=One Piece")
            .then()
                .statusCode(200);
    }

    @Test
    void testBuscarPorClassificacao() {
        given()
            .when().get("/mangas/classificacao/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testBuscarPorEditora() {
        given()
            .when().get("/mangas/editora/1")
            .then()
                .statusCode(200);
    }

    @Test
    void testAlterar() {
        MangaDTO dto = new MangaDTO(
            "One Piece - Novo Título",
            "Eiichiro Oda", 
            java.time.LocalDate.of(1997, 7, 22), 
            89.90, 
            "Adventure", 
            1, 
            1, 
            2, 
            123456789L, 
            987654321L 
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/mangas/" + id)
            .then()
                .statusCode(204);

        MangaResponseDTO manga = mangaService.findById(id);
        assertThat(manga.titulo(), is("One Piece - Novo Título"));
        assertThat(manga.preco(), is(89.90));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/mangas/" + id)
            .then()
                .statusCode(204);

        MangaResponseDTO manga = mangaService.findById(id);
        assertNull(manga);
    }
}
