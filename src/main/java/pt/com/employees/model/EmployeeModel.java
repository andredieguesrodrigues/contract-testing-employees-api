package pt.com.employees.model;

import lombok.Data;
import pt.com.util.Convert;

@Data
public class EmployeeModel extends Convert {
    private Long id;
    private boolean currentEmployee;
    private String name;
}
