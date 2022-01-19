package pt.com.employees.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Data
public class Employee {
	@Id
	private Long id;
	private boolean currentEmployee;
	private String name;
}
