package pt.com.employees.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import pt.com.employees.model.EmployeeModel;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockManager {

    public static void mockAPi() {
        EmployeeModel employeeModel = new EmployeeModel();

        employeeModel.setId(1L);
        employeeModel.setCurrentEmployee(true);
        employeeModel.setName("Andre Diegues");

        WireMockServer wireMockServer = new WireMockServer();

        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/employees")).willReturn(aResponse().withBody(employeeModel.toJSON()).
                withHeader("Content-Type", "application/json")));
        stubFor(get(urlEqualTo("/employees/1")).willReturn(aResponse()
                .withBody(employeeModel.toJSON()).withHeader("Content-Type", "application/json")));
        stubFor(post(urlEqualTo("/employees")).withRequestBody(equalToJson(employeeModel.toJSON()))
                .willReturn(aResponse().withStatus(201)));

    }

}
