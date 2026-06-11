package com.employeeproject.employeedetail.dto;

import com.employeeproject.employeedetail.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {

  private  Long id;
  @NotBlank(message = "Name of the employee cannot be empty")
  @Size(min=3,max=10,message = "Number of characters in name should be in the range: {3,10}")
  private  String name;
  @Email(message = "Email should be valid")
  @NotBlank(message="Email of the employee cannot be blank")

  private  String email;

  @NotNull(message = "Age of the employee cannot be blank")
  @Max(value = 80,message = "cannot be more than 80")
  @Min(value = 18,message = "cannot be less than 18")
  private  Integer age;

  @NotBlank(message="Role of the employee cannot be blank")
//  @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of employee can be USER or ADMIN")
  @EmployeeRoleValidation
  private String role;//Admin ,user

  @PastOrPresent(message ="Date of joining field cannot be future date")
  private  LocalDate dateOfJoining;

  @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
   private Boolean isActiveEmployee;

    @NotNull(message = "salary of the employee cannot be blank")
    @Digits(integer = 6,fraction=2,message = "The salary can be in the form XXXX.YY")
    @Positive(message = "Salary of the employee should be positive")
    @DecimalMin(value="100.50")
    @DecimalMax(value="100000.99")
    private Double salary;


}
