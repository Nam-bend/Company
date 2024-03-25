package com.example.demo5.employee.dto.response;

import com.example.demo5.employee.dto.request.EmployeeRequest;

public class EmployeeResponse extends EmployeeRequest {
    private long id ;

    public EmployeeResponse(){}

    public EmployeeResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id='" + id + '\'' +
                "} " + super.toString();
    }

}
