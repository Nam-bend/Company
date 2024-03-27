package com.example.demo5.employee.service;

import com.example.demo5.employee.dto.request.EmployeeRequest;
import com.example.demo5.employee.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
 //   List<EmployeeResponse> getAllEmployee();
    EmployeeResponse getEmployeeById(String id);

    EmployeeResponse create(EmployeeRequest request);

}
