package pt.com.employees.controllers;

import io.swagger.annotations.ApiOperation;
import pt.com.employees.entities.Employee;
import pt.com.employees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@ApiOperation(value = "Insert new employee in database",  notes = "Insert by Id, Name and currentEmployee")
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/employees").path("/{id}")
				.buildAndExpand(savedEmployee.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Read all employees from database",  notes = "Read all employees from database")
	@GetMapping
	public ResponseEntity<List<Employee>> listEmployees() {
		return ResponseEntity.ok().body(employeeRepository.findAll());
	}

	@ApiOperation(value = "Read all employees matching given Id",  notes = "Read all employees by Id")
	@GetMapping("/{id}")
	public ResponseEntity<Employee> searchById(@PathVariable Long id) {
		return ResponseEntity.ok().body(employeeRepository.findById(id).get());
	}

}


