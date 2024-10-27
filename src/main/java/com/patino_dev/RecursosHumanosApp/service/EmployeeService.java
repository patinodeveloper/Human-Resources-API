package com.patino_dev.RecursosHumanosApp.service;

import com.patino_dev.RecursosHumanosApp.model.Employee;
import com.patino_dev.RecursosHumanosApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository; // Inyeccion de Dependencia

    @Override
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee searchEmployeeById(Integer idEmployee) {
        // Retorna un valor de tipo OPTIONAL, indicamos que si no encuentra un empleado retorne NULL (.orElse(null))
        return employeeRepository.findById(idEmployee).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        // Recibe el objeto de tipo Employee y a su vez regresa el objeto que se haya insertado/actualizado
        // Se actualiza si la llave primaria es DIFERENTE de null, en caso contrario hace una insercion
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}
