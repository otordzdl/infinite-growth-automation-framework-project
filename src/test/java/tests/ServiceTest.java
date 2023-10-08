package tests;

import com.aventstack.extentreports.Status;
import io.github.otordzdl.infinitegrowth.core.base.BaseServiceTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import services.DepartmentsService;

public class ServiceTest extends BaseServiceTest {

    @Test(description = "Validaciòn del scheme del Response de /deparments")
    public void validacionScheme() {
        DepartmentsService departmentsService = new DepartmentsService(requester);
        String response = departmentsService.validateScheme();
        test.log(Status.PASS, "El scheme del response es correcto \n" + response);

    }
    @Test(description = "Validaciòn del Key deparments en Departments")
    public void validacionKeyDepartments() {
        DepartmentsService departmentsService = new DepartmentsService(requester);
       departmentsService.validateResponseContainsDepartments();
        test.log(Status.PASS, "La respuesta contiene la llave Departments");

    }

    @Test(description = "Validaciòn que el response contega un Departamento en Especifico")
     @Parameters("departamento")
    public void validacionDepartamentoEspecifico(String departamento) {
        DepartmentsService departmentsService = new DepartmentsService(requester);
        departmentsService.reviewIfResponseContainsSpecificDepartment(departamento);
        test.log(Status.PASS, "La respuesta contiene el departamente "+ departamento);

    }
}
