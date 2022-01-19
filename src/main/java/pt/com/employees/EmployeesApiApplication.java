package pt.com.employees;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import pt.com.employees.controllers.EmployeeController;
import pt.com.employees.entities.Employee;
import pt.com.employees.mock.WiremockManager;
import pt.com.employees.repositories.EmployeeRepository;
import pt.com.util.File;

@SpringBootApplication
public class EmployeesApiApplication {

    public static void main(String[] args) {

        if (File.readProperty("execution.mode").equals("real")){
            SpringApplication.run(EmployeesApiApplication.class, args);
        }
        else{
            WiremockManager.mockAPi();
        }
    }

}