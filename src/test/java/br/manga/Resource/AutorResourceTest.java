//OK

package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import br.manga.dto.AutorDTO;
import br.manga.dto.AutorResponseDTO;
import br.manga.service.AutorService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class AutorResourceTest {

    @Inject
    AutorService service;

    static Long id;

    @Test
    void testCreate() {
        AutorDTO dto = new AutorDTO("Autor Teste", "Brasileira");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/autores")
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nome", is("Autor Teste"),
                      "nacionalidade", is("Brasileira"));
    }

    @Test
    void testBuscarPorNome() {
        AutorDTO dto = new AutorDTO("Autor Para Buscar", "Japonesa");
        service.create(dto);

        given()
    .when().get("/autores/nome/Autor Para Buscar")
    .then()
        .statusCode(200)
        .body("size()", is(1),
              "[0].nome", is("Autor Para Buscar"));
    }

    @Test
void testBuscarPorNacionalidade() {
    given()
        .when().get("/autores/nacionalidade/Japonesa")
        .then()
            .statusCode(200)
            .body("size()", is(8)); 
}

@Test
void testGetAll() {
    given()
            .when().get("/autores")
            .then()
                .statusCode(200); 
}

@Test
void testGetById() {
    given()
        .when().get("/autores/1")
        .then()
            .statusCode(200)
            .body("nome", is("Masashi Kishimoto"));
}

    @Test
    void testUpdate() {
        AutorDTO dto = new AutorDTO("Autor Original", "Francesa");
        id = service.create(dto).id();

        AutorDTO dtoAtualizado = new AutorDTO("Autor Atualizado", "Alemã");

        given()
            .contentType(ContentType.JSON)
            .body(dtoAtualizado)
            .when().put("/autores/" + id)
            .then()
                .statusCode(204);

        AutorResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("Autor Atualizado"));
        assertThat(response.nacionalidade(), is("Alemã"));
    }

    @Test
    void testDelete() {
        AutorDTO dto = new AutorDTO("Autor Excluir", "Mexicana");
        id = service.create(dto).id();

        given()
            .when().delete("/autores/" + id)
            .then()
                .statusCode(204);

        given()
            .when().get("/autores/" + id)
            .then()
                .statusCode(404);
    }
}
