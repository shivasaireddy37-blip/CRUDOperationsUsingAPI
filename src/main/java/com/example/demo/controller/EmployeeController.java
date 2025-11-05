package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/createEmployee")
	public ResponseEntity<Map<String, Object>> createEmployee(@Valid @RequestBody EmployeeRequest request) {
		Employee created = employeeService.createEmployee(request);
		Map<String, Object> response = Map.of("message", "Employee created  successfully with ID : " + created.getId(),
				"employee", created);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getEmployeeByEmail/{email}/method/{methodType}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email, @PathVariable String methodType) {
		return ResponseEntity.ok(employeeService.findEmployeeByEmail(email, methodType));

	}

	@GetMapping("/getEmployeeByFirstName/{firstname}/method/{methodType}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String firstname,
			@PathVariable String methodType) {
		return ResponseEntity.ok(employeeService.findEmployeeByName(firstname, methodType));

	}

	@PutMapping("/updateEmployee/email/{email}/method/{methodType}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String email, @PathVariable String methodType,
			@RequestBody Map<String, String> requestBody) {

		String lastname = requestBody.get("lastname");
		String phone = requestBody.get("phone");
		String address = requestBody.get("address");

		Employee updated = employeeService.updateEmployee(email, lastname, phone, address, methodType);
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/updatePhone/email/{email}")
	public ResponseEntity<Map<String, Object>> updateEmployeePhoneNumber(@PathVariable String email,
			@RequestBody Map<String, String> requestBody) {

		String phone = requestBody.get("phone");

		Employee updated = employeeService.updateEmployeePhoneNumber(email, phone);

		Map<String, Object> response = Map.of("message",
				"Phone number updated successfully for Employee ID: " + updated.getId(), "employee", updated);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/deleteEmployeeByEmail/{email}/method/{methodType}")
	public ResponseEntity<Map<String, Object>> deleteEmployeeByEmail(@PathVariable String email,
			@PathVariable String methodType) {

		String message = employeeService.deleteEmployeeByEmail(email, methodType);

		Map<String, Object> response = Map.of("message", "Employee deleted successfully!", "details", message);

		return ResponseEntity.ok(response);
	}

}
