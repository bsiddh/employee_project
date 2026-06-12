package com.employeeproject.employeedetail.services;

import com.employeeproject.employeedetail.dto.EmployeeDTO;
import com.employeeproject.employeedetail.entities.EmployeeEntity;
import com.employeeproject.employeedetail.exception.ResourceNotFoundException;
import com.employeeproject.employeedetail.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//       Optional<EmployeeEntity> employeeEntity   = employeeRepository.findById(id);
//       return  modelMapper.map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDTO.class));

        return employeeRepository.findById(id).map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity>  employeeEntities= employeeRepository.findAll();
      return  employeeEntities
                .stream()
                .map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee) {

       EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
       return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
      isExistsByEmployeeId(employeeId);
      EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
      employeeEntity.setId(employeeId);
      EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
      return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);

    }

    public void isExistsByEmployeeId(Long employeeId){
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists)  throw new ResourceNotFoundException("Employee  not found "+employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExistsByEmployeeId(employeeId);
    employeeRepository.deleteById(employeeId);
    return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String,Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) ->{
          Field fieldToBeUpdated =  ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
      return  modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }


}
