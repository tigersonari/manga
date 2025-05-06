//OK

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import br.manga.dto.EditoraDTO;
import br.manga.dto.EditoraResponseDTO;
import br.manga.service.EditoraService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EditoraResourceTest {

    @Inject
    EditoraService service;

    static Long id = null;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/editoras")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetById() {
        given()
            .when().get("/editoras/1")
            .then()
                .statusCode(200)
                .body("nome", is("Panini"));
    }

    @Test
    void testGetByNome() {
        given()
            .when().get("/editoras/nome/Editora Teste")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetBySede() {
        given()
            .when().get("/editoras/sede/São Paulo")
            .then()
                .statusCode(200);
    }

    @Test
    void testGetByAnoFundacao() {
        given()
            .when().get("/editoras/ano-fundacao/1990")
            .then()
                .statusCode(200);
    }

    @Test
    void testCreate() {
        EditoraDTO dto = new EditoraDTO("Editora Teste CRUD", "Cidade Teste", LocalDate.of(2020, 1, 1));

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/editoras")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("Editora Teste CRUD"));
    }

    @Test
    void testUpdate() {
        
        EditoraDTO dtoOriginal = new EditoraDTO("Editora Original", "Cidade X", LocalDate.of(2022, 2, 2));
        id = service.create(dtoOriginal).id();

    
        EditoraDTO dtoAtualizado = new EditoraDTO("Editora Atualizada", "Cidade Y", LocalDate.of(2023, 3, 3));

        given()
            .contentType(ContentType.JSON)
            .body(dtoAtualizado)
            .when().put("/editoras/" + id)
            .then()
                .statusCode(204);

        EditoraResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("Editora Atualizada"));
        assertThat(response.sede(), is("Cidade Y"));
    }

    @Test
void testDelete() {
    EditoraDTO dto = new EditoraDTO("Editora Para Deletar", "Cidade Z", LocalDate.of(2021, 1, 1));
    Long deleteId = service.create(dto).id();

    given()
        .when().delete("/editoras/" + deleteId)
        .then()
            .statusCode(204);

    given()
        .when().get("/editoras/" + deleteId)
        .then()
            .statusCode(404); 
}

}
