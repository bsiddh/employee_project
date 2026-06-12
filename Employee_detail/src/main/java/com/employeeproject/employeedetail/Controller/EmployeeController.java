package com.employeeproject.employeedetail.Controller;

import com.employeeproject.employeedetail.advices.ApiResponse;
import com.employeeproject.employeedetail.dto.EmployeeDTO;
import com.employeeproject.employeedetail.exception.ResourceNotFoundException;
import com.employeeproject.employeedetail.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return  employeeDTO.map(employeeDTO1->ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()->new ResourceNotFoundException("Employee  not found "+id));

    }



    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false,name="inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(new ApiResponse<>(savedEmployee), HttpStatus.CREATED);

    }

    @PutMapping(path = "/{employeeId}")
  ResponseEntity<EmployeeDTO>   updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));

    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
     boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
     if(gotDeleted) return ResponseEntity.ok(true);
     return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
   public ResponseEntity<EmployeeDTO>   updatePartialEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
