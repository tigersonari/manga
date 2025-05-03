package br.manga.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Order;
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
    AdminService service;

    @Test
    @Order(1)
    void testFindById() {
        AdminDTO admin = new AdminDTO("Admin Teste", "admin@teste.com", "12345678901", "Rua Teste 123", "TOTAL");
        Long id = service.create(admin).id();

        given()
            .when().get("/admins/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nome", is("Admin Teste"));
    }

    @Test
    @Order(2)
    void testFindByPermissao() {
        AdminDTO admin = new AdminDTO("Admin Permissao", "permissao@teste.com", "12345678901", "Rua Permissao 124", "PARCIAL");
        service.create(admin);

        given()
            .when().get("/admins/permissao/PARCIAL")
            .then()
                .statusCode(200)
                .body("[0].nome", is("Admin Permissao"));
    }

    @Test
    @Order(3)
    void testCreate() {
        AdminDTO admin = new AdminDTO("Admin Novo", "novo@teste.com", "12345678901", "Rua Nova 125", "TOTAL");

        given()
            .contentType(ContentType.JSON)
            .body(admin)
            .when().post("/admins")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", is("Admin Novo"))
                .body("permissao", is("TOTAL"));
    }

    static Long id = null;

    @Test
    @Order(4)
    void testUpdate() {
        AdminDTO admin = new AdminDTO("Admin Original", "original@teste.com", "12345678901", "Rua Original 126", "ORIGINAL");
        id = service.create(admin).id();

        AdminDTO updated = new AdminDTO("Admin Atualizado", "atualizado@teste.com", "12345678901", "Rua Atualizada 126", "ATUALIZADO");

        given()
            .contentType(ContentType.JSON)
            .body(updated)
            .when().put("/admins/" + id)
            .then()
                .statusCode(204);

        AdminResponseDTO response = service.findById(id);
        assertThat(response.nome(), is("Admin Atualizado"));
        assertThat(response.permissao(), is("ATUALIZADO"));
    }

    @Test
    @Order(5)
    void testDelete() {
        AdminDTO admin = new AdminDTO("Admin Deletar", "deletar@teste.com", "12345678901", "Rua Deletar 127", "DELETAR");
        Long idDeletar = service.create(admin).id();

        given()
            .when().delete("/admins/" + idDeletar)
            .then()
                .statusCode(204);

        try {
            AdminResponseDTO response = service.findById(idDeletar);
            assertNull(response);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Admin não encontrado"));
        }
    }
}