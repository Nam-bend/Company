package com.example.demo5.employee.service.serviceImpl;

import com.example.demo5.employee.dto.request.EmployeeRequest;
import com.example.demo5.employee.dto.response.EmployeeResponse;
import com.example.demo5.employee.entity.EmployeeEntity;
import com.example.demo5.employee.repository.EmployeeRepository;
import com.example.demo5.employee.service.EmployeeService;
import com.example.demo5.employee.service.mapping.EmployeeMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo5.employee.service.mapping.EmployeeMapping.convertDtoToEntity;
import static com.example.demo5.employee.service.mapping.EmployeeMapping.convertEntityToResponse;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    public final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        log.info(" === Start API get all === ");
        log.info("Getting all employees...");
        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<EmployeeResponse> response = convertEntitiesToResponses(employees);
        log.info("Total employees found: {}", response.size());
        log.info(" === Finish API create new user, User id: {} ===", response);
        return response;
    }

    private List<EmployeeResponse> convertEntitiesToResponses(List<EmployeeEntity> employees) {
        return employees.stream()
                .map(EmployeeMapping::convertEntityToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public EmployeeResponse getEmployeeById(String id) {
        return null;
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        // Log the start of the API call
        log.info(" === Start API create new user === ");

        // Log the request body
        log.info(" === RequestBody : {} ===", request);

        // Add some logic before creating EmployeeEntity
        if (request.getName() == null || request.getName().isEmpty()) {
            log.warn("Employee name cannot be empty");
            throw new IllegalArgumentException("Employee name cannot be empty");
        }

        // Convert DTO object to Entity object
        EmployeeEntity entity = convertDtoToEntity(request);
        log.info("Converted EmployeeRequest to EmployeeEntity: {}", entity);

        // Save the Entity object to the database
        entity = employeeRepository.save(entity);
        log.info("Saved EmployeeEntity to the database: {}", entity);

        // Log information after creating the object
        log.info("New employee created: {}", entity);

        // Convert Entity object to Response object
        EmployeeResponse response = convertEntityToResponse(entity);

        // Log information about the completion of the API call
        log.info(" === Finish API create new user, User id: {} ===", response.getId());

        // Return the Response object to the client
        return response;
    }
}
