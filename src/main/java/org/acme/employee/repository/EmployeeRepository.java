package org.acme.employee.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.employee.model.entity.Employee;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
}