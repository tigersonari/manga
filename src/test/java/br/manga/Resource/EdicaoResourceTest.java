package br.manga.resource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import br.manga.dto.EdicaoDTO;
import br.manga.dto.EdicaoResponseDTO;
import br.manga.service.EdicaoService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EdicaoResourceTest {

    @Inject
    EdicaoService service;

    static Long id;

    @Test
    void testCreate() {
        EdicaoDTO dto = new EdicaoDTO(
            1, 
            "Português",
            LocalDate.of(2025, 1, 1),
            "13.5x20.5",
            "Test Edição Vol. 1",
            0, 
            0, 
            0, 
            1L 
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/edicoes")
            .then()
                .log().all() 
                .statusCode(201)
                .body("id", notNullValue(),
                      "volume", is(1),
                      "idioma", is("Português"),
                      "formato.id", is(0), 
                      "status.id", is(0)); 
    }

    @Test
    void testGetAll() {
        given()
            .when().get("/edicoes")
            .then()
                .statusCode(200);  
    }

    @Test
    void testGetById() {
        given()
            .when().get("/edicoes/1")
            .then()
                .statusCode(200)
                .body("titulo", is("Naruto Vol. 1"));
    }

    @Test
    void testFindByManga() {
        EdicaoDTO dto = new EdicaoDTO(3, "Português", LocalDate.of(2025, 1, 1), "13.5x20.5", "Test Vol. 3", 0, 0, 0, 1L);
        service.create(dto);

        given()
            .when().get("/edicoes/manga/1")
            .then()
                .statusCode(200)
                .body("size()", is(4));
    }



    @Test
    void testFindByVolumeAndManga() {
        EdicaoDTO dto = new EdicaoDTO(6, "Português", LocalDate.of(2025, 1, 1), "13.5x20.5", "Test Vol. 6", 0, 0, 0, 1L);
        service.create(dto);

        given()
            .when().get("/edicoes/volume/6/manga/1")
            .then()
                .statusCode(200)
                .body("volume", is(6),
                      "manga.id", is(1)); 
    }


    @Test
    void testUpdate() {
        EdicaoDTO dto = new EdicaoDTO(6, "Português", LocalDate.now(), "13x18cm", "Edição Teste", 1, 1, 1, 1L);
        id = service.create(dto).id();

        EdicaoDTO updated = new EdicaoDTO(7, "Espanhol", LocalDate.now(), "15x20cm", "Edição Atualizada", 1, 1, 1, 1L);

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/edicoes/" + id)
            .then()
                .statusCode(204);

        EdicaoResponseDTO response = service.findById(id);
        assertThat(response.titulo(), is("Edição Atualizada"));
    }

    @Test
    void testDelete() {
        EdicaoDTO dto = new EdicaoDTO(9, "Português", LocalDate.now(), "13x18cm", "Edição Teste", 1, 1, 1, 1L);
        id = service.create(dto).id();

        given()
            .when().delete("/edicoes/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/edicoes/" + id)
            .then()
                .statusCode(404);
    }
}
