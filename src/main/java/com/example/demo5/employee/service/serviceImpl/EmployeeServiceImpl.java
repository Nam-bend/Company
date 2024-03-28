package com.example.demo5.employee.service.serviceImpl;
import com.example.demo5.employee.dto.request.EmployeeRequest;
import com.example.demo5.employee.dto.response.EmployeeResponse;
import com.example.demo5.employee.entity.EmployeeEntity;
import com.example.demo5.employee.exception.EmployeeNotFoundException;
import com.example.demo5.employee.repository.EmployeeRepository;
import com.example.demo5.employee.service.EmployeeService;
import com.example.demo5.employee.service.mapping.EmployeeMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@Transactional(readOnly = true,timeout = 30)
    public List<EmployeeResponse> getAllEmployee() {
        log.info(" === Start API get all === ");
        log.info("Getting all employees...");
        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<EmployeeResponse> response = new ArrayList<>();

        for (EmployeeEntity employee : employees) {
            EmployeeResponse employeeResponse = EmployeeMapping.convertEntityToResponse(employee);
            response.add(employeeResponse);
        }
        log.info("Total employees found: {}", response.size());
        log.info(" === Finish API create new user, User id: {} ===", response);
        return response;
    }
    @Override
    @Transactional(readOnly = true,timeout = 15)
    public EmployeeResponse getEmployeeById(long id) {
        log.info("Start API get employee by id : {} === ", id);
        try {
            Optional<EmployeeEntity> optional = employeeRepository.findById(id);
            if (optional.isPresent()) {
                EmployeeEntity employeeEntity = optional.get();
                EmployeeResponse response = convertEntityToResponse(employeeEntity);
                log.info("Finish API get employee by id : {} , employee : {} ===", id, response);
                return response;
            } else {
                log.info("Employee with id {} not found", id);
                throw new EmployeeNotFoundException("Employee not found with id : " + id);
            }
        } catch (EmployeeNotFoundException ex) {
            throw ex;
        } catch (TransactionTimedOutException e) {
            log.error("Transaction timed out while getting employee by id : {}", id);
            throw new RuntimeException("Transaction timed out while getting employee by id: " + id);
        } catch (Exception e) {
            log.error("Error occurred while getting employee by id : {}", e.getMessage());
            throw new RuntimeException("Server error");
        }
    }

//        log.info("start API get employee by id : {} === ", id);
//        try {
//            Optional<EmployeeEntity> optional = employeeRepository.findById(id);
//            if (optional.isPresent()) {
//                EmployeeEntity employeeEntity = optional.get();
//                EmployeeResponse response = convertEntityToResponse(employeeEntity);
//                log.info(" === Finish API get employee by id : {} , employee : {} ===", id, response);
//                return response;
//            } else {
//                log.info(" === Finish Api get employee by id : {} ;Employee not found ", id);
//                throw new EmployeeNotFoundException("Employee not found with id : " + id);
//            }
//        } catch (EmployeeNotFoundException ex) {
//            throw ex;
//        } catch (Exception e) {
//            log.error(" === Error occurred while getting employee by id : {} === ", e.getMessage());
//            throw new RuntimeException(" Server error ");
//        }


    @Override
    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        log.info(" === Start API create new user === ");
        log.info(" === RequestBody : {} ===", request);
        if (request.getName() == null || request.getName().isEmpty()) {
            log.warn("Employee name cannot be empty");
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        EmployeeEntity entity = convertDtoToEntity(request);
        log.info("Converted EmployeeRequest to EmployeeEntity: {}", entity);
        entity = employeeRepository.save(entity);
        log.info("Saved EmployeeEntity to the database: {}", entity);
        log.info("New employee created: {}", entity);
        EmployeeResponse response = convertEntityToResponse(entity);
        log.info(" === Finish API create new user, User id: {} ===", response.getId());
        return response;
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info(" === Start api delete employee by id : {} === ", id);
        Optional<EmployeeEntity> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            if (optional.isPresent()) {
                employeeRepository.deleteById(id);
                log.info("Employee with id {} deleted successfully", id);
            } else {
                log.info("Employee with id {} not found", id);
                throw new EmployeeNotFoundException("Employee not found with id : " + id);
            }
            log.info(" === Finish API delete employee by id: {} ===", id);
        }
    }
}


