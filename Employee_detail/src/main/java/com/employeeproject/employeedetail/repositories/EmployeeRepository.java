package com.employeeproject.employeedetail.repositories;

import com.employeeproject.employeedetail.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>{




}
