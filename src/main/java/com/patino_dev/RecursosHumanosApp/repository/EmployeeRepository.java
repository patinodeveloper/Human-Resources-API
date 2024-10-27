package com.patino_dev.RecursosHumanosApp.repository;

import com.patino_dev.RecursosHumanosApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//  Indicamos el tipo de Clase Entidad y el tipo de Llave Primaria
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //  Importa los metodos basicos de CRUD
}
