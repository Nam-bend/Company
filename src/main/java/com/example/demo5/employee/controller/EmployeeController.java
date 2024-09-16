package com.example.demo5.employee.controller;

import com.example.demo5.employee.dto.request.EmployeeRequest;
import com.example.demo5.employee.dto.response.EmployeeResponse;
import com.example.demo5.employee.exception.EmployeeNotFoundException;
import com.example.demo5.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dv1/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest request) {
        try {
            EmployeeResponse response = employeeService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            log.error("Bad request: {}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        log.info(" === Start API get all === ");
        try {
            List<EmployeeResponse> employees = employeeService.getAllEmployee();
            log.info(" === Finish API get all === ");
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable long id) {
        log.info(" === Start API get employee by id: {} === ", id);
        try {
            EmployeeResponse employee = employeeService.getEmployeeById(id);
            log.info(" === Finish API get employee by id: {}, Employee: {} === ", id, employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeNotFoundException ex) {
            log.error("Employee not found: {}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

