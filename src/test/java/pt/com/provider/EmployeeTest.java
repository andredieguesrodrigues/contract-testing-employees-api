package pt.com.provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.google.gson.Gson;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import pt.com.employees.EmployeesApiApplication;
import pt.com.employees.controllers.EmployeeController;
import pt.com.employees.entities.Employee;

import javax.naming.ldap.Control;

@RunWith(PactRunner.class)
@Provider("employee")
@PactBroker(host = "localhost", port = "9292")
//@PactFolder("pacts")
public class EmployeeTest {

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 8080);

    //region Inicializacao Classe Main

    private static ConfigurableWebApplicationContext application;


    @BeforeClass
    public static void start() {
//        application = (ConfigurableWebApplicationContext)
//                SpringApplication.run(EmployeesApiApplication.class);

    }

    //endregion

    @State("Employee 1 exist")
    public void toGetState() {
    }

}
