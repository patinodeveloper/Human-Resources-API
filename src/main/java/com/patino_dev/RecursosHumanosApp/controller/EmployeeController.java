package com.patino_dev.RecursosHumanosApp.controller;

import com.patino_dev.RecursosHumanosApp.Exception.ResourceNotFoundException;
import com.patino_dev.RecursosHumanosApp.model.Employee;
import com.patino_dev.RecursosHumanosApp.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rh-app")
@CrossOrigin // Para recibir peticiones desde otro puerto (front-end)
public class EmployeeController {
    private Logger logger =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.listEmployees();
        employees.forEach(employee -> logger.info(employee.toString()));
        return employees;
    }

    @GetMapping("/employees/{idEmployee}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer idEmployee) {
        Employee employee = employeeService.searchEmployeeById(idEmployee);
        if (employee == null) {
            throw new ResourceNotFoundException("No se encontro el empleado id: " + idEmployee);
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee) {
        logger.info("Empleado a agregar: " + employee.toString());
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{idEmployee}")
    public ResponseEntity<Employee>
        updateEmployee(@PathVariable Integer idEmployee, @RequestBody Employee receivedEmployee) {
        Employee employee =  employeeService.searchEmployeeById(idEmployee);
        if (employee == null)
            throw new ResourceNotFoundException("El id recibido no existe: " + idEmployee);
        employee.setName(receivedEmployee.getName());
        employee.setDepartment(receivedEmployee.getDepartment());
        employee.setSalary(receivedEmployee.getSalary());
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/{idEmployee}")
    public ResponseEntity<Map<String, Boolean>> // Indica si se elimino el registro o no
        deleteEmployee(@PathVariable Integer idEmployee) {
        Employee employee = employeeService.searchEmployeeById(idEmployee);
        if (employee == null) {
            throw new ResourceNotFoundException("El id recibido no existe: " + idEmployee);
        }
        employeeService.deleteEmployee(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
