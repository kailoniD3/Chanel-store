package chanel.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chanel.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
