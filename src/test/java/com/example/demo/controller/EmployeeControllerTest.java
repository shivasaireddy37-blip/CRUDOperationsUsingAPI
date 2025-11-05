package com.example.demo.controller;

import com.example.demo.service.EmployeeService;
import com.example.demo.model.Employee;
import com.example.demo.dto.EmployeeRequest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    public EmployeeControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstname("shiva");
        request.setLastname("sai");
        request.setEmail("shiva@gmail.com");

        Employee savedEmployee = new Employee();
        savedEmployee.setId(1L);
        savedEmployee.setFirstname("shiva");
        savedEmployee.setEmail("shiva@gmail.com");

        when(employeeService.createEmployee(request)).thenReturn(savedEmployee);
        ResponseEntity<Map<String, Object>> response = employeeController.createEmployee(request);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().get("message").toString().contains("successfully"));
        verify(employeeService, times(1)).createEmployee(request);
    }

    @Test
    void testGetEmployeeByEmail() {
        String email = "shiva";
        String methodType = "jpa";

        Employee emp = new Employee();
        emp.setEmail(email);
        emp.setFirstname("shiva");

        when(employeeService.findEmployeeByEmail(email, methodType)).thenReturn(emp);

        ResponseEntity<Employee> response = employeeController.getEmployeeByEmail(email, methodType);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("shiva", response.getBody().getFirstname());
    }

    @Test
    void testDeleteEmployeeByEmail() {
        String email = "shiva@gmail.com";
        String methodType = "hql";

        when(employeeService.deleteEmployeeByEmail(email, methodType)).thenReturn("Employee deleted successfully");

        ResponseEntity<Map<String, Object>> response = employeeController.deleteEmployeeByEmail(email, methodType);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().get("message").toString().contains("deleted"));
    }
}
