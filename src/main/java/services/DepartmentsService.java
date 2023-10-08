package services;

import io.github.otordzdl.infinitegrowth.core.base.BaseService;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;


public class DepartmentsService extends BaseService {
    public DepartmentsService(RequestSpecification requester) {
        super(requester);
    }

    public String validateScheme() {

        Response response = requester
                .when()
                .get("menu/departments");

        Assert.assertEquals(response.getStatusCode(), 200, "Validación de Status code de GET de departments");
        response.then().body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/java/services/schemes/departments_scheme.json")));

        return response.asPrettyString();
    }


    public void validateResponseContainsDepartments(){
        Response response = requester
                .when()
                .get("menu/departments");

        Assert.assertEquals(response.getStatusCode(), 200, "Validación de Status code de GET de departments");
        response.then().body("$",hasKey("departments"));

    }

    public void reviewIfResponseContainsSpecificDepartment(String deparment){
        Response response = requester
                .when()
                .get("menu/departments");

        Assert.assertEquals(response.getStatusCode(), 200, "Validación de Status code de GET de departments");
        response.then().body(String.format("departments.find { it.name == '%s' }",deparment), notNullValue());
    }


}
