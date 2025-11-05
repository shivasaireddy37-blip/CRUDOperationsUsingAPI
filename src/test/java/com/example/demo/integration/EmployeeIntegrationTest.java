package com.example.demo.integration;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateEmployeeEndpoint() {
        EmployeeRequest req = new EmployeeRequest();
        req.setFirstname("shiva");
        req.setEmail("shiva@gmail.com");

        ResponseEntity<Employee> response = restTemplate.postForEntity(
                "/api/employees/createEmployee", req, Employee.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getEmail()).isEqualTo("shiva@gmail.com");
    }
}

