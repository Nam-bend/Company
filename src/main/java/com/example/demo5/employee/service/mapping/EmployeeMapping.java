package com.example.demo5.employee.service.mapping;

import com.example.demo5.employee.dto.request.EmployeeRequest;
import com.example.demo5.employee.dto.response.EmployeeResponse;
import com.example.demo5.employee.entity.EmployeeEntity;

public class EmployeeMapping {
    public static EmployeeEntity convertDtoToEntity(EmployeeRequest dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setNumberOfEmployees(dto.getNumberOfEmployees());
        entity.setNumberOfWorkingDays(dto.getNumberOfWorkingDays());
        return entity;
    }

    public static EmployeeResponse convertEntityToResponse(EmployeeEntity entity) {
     EmployeeResponse dto = new EmployeeResponse();
       dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setNumberOfEmployees(entity.getNumberOfEmployees());
        dto.setNumberOfWorkingDays(entity.getNumberOfWorkingDays());
        return dto;
    }
}

