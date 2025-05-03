package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import br.manga.dto.AdminDTO;
import br.manga.dto.AdminResponseDTO;
import br.manga.service.AdminService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class AdminResourceTest {

    @Inject
    AdminService adminService;

    static Long id;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/admins")
            .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        AdminDTO dto = new AdminDTO(
            "admin1", 
            "senha123"
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/admins")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("login", is("admin1"))
                .extract().path("id");
    }

    @Test
    void testBuscarPorId() {
        given()
            .when().get("/admins/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()));
    }

    @Test
    void testAlterar() {
        AdminDTO dto = new AdminDTO(
            "adminAtualizado",
            "novaSenha",
            "updated_email@example.com",
            "Updated Admin Name",
            "987654321"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/admins/" + id)
            .then()
                .statusCode(204);

        AdminResponseDTO admin = adminService.findById(id);
        assertThat(admin.login(), is("adminAtualizado"));
    }

    @Test
    void testApagar() {
        given()
            .when().delete("/admins/" + id)
            .then()
                .statusCode(204);

        AdminResponseDTO admin = adminService.findById(id);
        assertNull(admin);
    }
}
