package com.employeeproject.employeedetail.Controller;

import com.employeeproject.employeedetail.dto.EmployeeDTO;
import com.employeeproject.employeedetail.entities.EmployeeEntity;
import com.employeeproject.employeedetail.repositories.EmployeeRepository;
import com.employeeproject.employeedetail.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @GetMapping(path="/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message : cryptic code";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createEmployee(inputEmployee);
    }

    @PutMapping
    String updateEmployeeById(){
        return "hello from put";
    }
}
