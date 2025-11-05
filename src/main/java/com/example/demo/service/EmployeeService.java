package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(EmployeeRequest request) {
		try {
			Employee employee = new Employee();
			employee.setFirstname(request.getFirstname());
			employee.setLastname(request.getLastname());
			employee.setEmail(request.getEmail());
			employee.setAddress(request.getAddress());
			employee.setPhone(request.getPhone());
			Employee saved = employeeRepository.save(employee);
			saved.getId();
			return saved;

		} catch (DataIntegrityViolationException ex) {
			throw new RuntimeException("mail exits , use different mail");
		}

	}

// Find Employee Details using Email(JPA/HQL/Native)
	public Employee findEmployeeByEmail(String email, String methodType) {
		return switch (methodType.toLowerCase()) {
		case "jpa" -> employeeRepository.findByEmail(email)
				.orElseThrow(() -> new EmployeeNotFoundException("Not found with email: " + email));
		case "hql" -> employeeRepository.findByEmailHQL(email)
				.orElseThrow(() -> new EmployeeNotFoundException("Not found (HQL): " + email));
		case "native" -> employeeRepository.findByEmailNative(email)
				.orElseThrow(() -> new EmployeeNotFoundException("Not found (Native): " + email));
		default -> throw new RuntimeException("Invalid method type (use: jpa, hql, native)");
		};
	}

//find Employee Details using first name(JPA/HQL/Native)
	public List<Employee> findEmployeeByName(String firstname, String methodType) {
		List<Employee> list = switch (methodType.toLowerCase()) {
		case "jpa" -> employeeRepository.findByFirstnameIgnoreCase(firstname);
		case "hql" -> employeeRepository.findByFirstnameHQL(firstname);
		case "native" -> employeeRepository.findByFirstnameNative(firstname);
		default -> throw new RuntimeException("Invalid method type (use: jpa, hql, native)");
		};

		if (list.isEmpty())
			throw new EmployeeNotFoundException("No employees found with name: " + firstname);
		return list;
	}

//update employee details using email
	public Employee updateEmployee(String email, String lastname, String phone, String address, String methodType) {
		switch (methodType.toLowerCase()) {
		case "jpa" -> {
			Employee emp = employeeRepository.findByEmail(email)
					.orElseThrow(() -> new EmployeeNotFoundException("Not found: " + email));
			emp.setLastname(lastname);
			emp.setPhone(phone);
			emp.setAddress(address);
			return employeeRepository.save(emp);
		}

		case "hql" -> {
			int updated = employeeRepository.updateEmployeeHQL(email, lastname, phone, address);
			if (updated == 0)
				throw new EmployeeNotFoundException("Not found (HQL): " + email);
			return employeeRepository.findByEmail(email)
					.orElseThrow(() -> new EmployeeNotFoundException("Not found after HQL update: " + email));
		}

		case "native" -> {
			int updated = employeeRepository.updateEmployeeNative(email, lastname, phone, address);
			if (updated == 0)
				throw new EmployeeNotFoundException("Not found (Native): " + email);
			return employeeRepository.findByEmail(email)
					.orElseThrow(() -> new EmployeeNotFoundException("Not found after Native update: " + email));
		}

		default -> throw new RuntimeException("Invalid method type (use: jpa, hql, native)");
		}
	}

//update phone number using email
	public Employee updateEmployeePhoneNumber(String email, String phone) {
		Employee employee = employeeRepository.findByEmail(email)
				.orElseThrow(() -> new EmployeeNotFoundException(" employee not found with email" + email));
		employee.setPhone(phone);
		return employeeRepository.save(employee);
	}

//delete employee using email
	@Transactional
	public String deleteEmployeeByEmail(String email, String methodType) {
		switch (methodType.toLowerCase()) {
		case "jpa" -> {
			Employee emp = employeeRepository.findByEmail(email)
					.orElseThrow(() -> new EmployeeNotFoundException("Not found: " + email));
			employeeRepository.delete(emp);
			return "Deleted using JPA successfully";
		}
		case "hql" -> {
			int deleted = employeeRepository.deleteByEmailHQL(email);
			if (deleted == 0)
				throw new EmployeeNotFoundException("Not found (HQL): " + email);
			return "Deleted using HQL successfully";
		}
		case "native" -> {
			int deleted = employeeRepository.deleteByEmailNative(email);
			if (deleted == 0)
				throw new EmployeeNotFoundException("Not found (Native): " + email);
			return "Deleted using Native SQL successfully";
		}
		default -> throw new RuntimeException("Invalid method type (use: jpa, hql, native)");
		}
	}

}
