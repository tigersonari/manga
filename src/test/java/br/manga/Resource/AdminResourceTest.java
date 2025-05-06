//OK

package br.manga.resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;

import br.manga.dto.AdminDTO;
import br.manga.service.AdminService;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;


@QuarkusTest
public class AdminResourceTest {

    @Inject
    AdminService adminService;

    @Test
    void testFindByPermissao() {
        given()
            .when().get("/admins/permissao/FULL_ACCESS")
            .then()
            .statusCode(200);
    }


    @Test
    void testCreate() {
        AdminDTO dto = new AdminDTO("admin1", "admin1@gmail.com", "senha123", "Rua 23o3", "FULL_ACCESS");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/admins")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("nome", equalTo("admin1"))
            .body("permissao", equalTo("FULL_ACCESS"));
    }

    @Test
    void testUpdate() {
        
        AdminDTO dto = new AdminDTO("admin2", "admin2@gmail.com", "senha123", "Rua 23owa3", "FULL_ACCESS");
        Long id = adminService.create(dto).id();

        AdminDTO atualizado = new AdminDTO("admin2", "admin2UPDATED@gmail.com", "senha123", "Rua 23owa3", "FULL_ACCESS");

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/admins/" + id)
            .then()
            .statusCode(204);
    }

    @Test
    void testDelete() {
        
        AdminDTO dto = new AdminDTO("admin3", "admin3@gmail.com", "senha123", "Rua 23owa3", "FULL_ACCESS");;
        Long id = adminService.create(dto).id();

        given()
            .when().delete("/admins/" + id)
            .then()
            .statusCode(204);
    }
}
