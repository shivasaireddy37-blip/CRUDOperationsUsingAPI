package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);

	List<Employee> findByFirstnameIgnoreCase(String firstname);

	// fetch by email using HQL
	@Query("SELECT e FROM Employee e WHERE e.email=?1")
	Optional<Employee> findByEmailHQL(String email);

	// fetch by email using Native SQl
	@Query(value = "SELECT * FROM  Employee  WHERE email = ?1", nativeQuery = true)
	Optional<Employee> findByEmailNative(String email);

	// fetch by name using HQL
	@Query("Select e from Employee e where LOWER(e.firstname)=LOWER(?1)")
	List<Employee> findByFirstnameHQL(String firstname);

	// fetch by name using native
	@Query(value = "select * from Employee where LOWER(firstname)=LOWER(?1)", nativeQuery = true)
	List<Employee> findByFirstnameNative(String firstname);

	// update employee using HQL
	@Modifying
	@Transactional
	@Query("UPDATE Employee e SET e.lastname = ?2, e.phone = ?3, e.address = ?4 WHERE e.email = ?1")
	int updateEmployeeHQL(String email, String lastname, String phone, String address);

	// update employee using Native SQL
	@Modifying
	@Transactional
	@Query(value = "UPDATE Employee SET lastname = ?2, phone = ?3, address = ?4 WHERE email = ?1", nativeQuery = true)
	int updateEmployeeNative(String email, String lastname, String phone, String address);

	// delete employee using HQL
	@Modifying
	@Transactional
	@Query("DELETE FROM Employee e WHERE e.email = ?1")
	int deleteByEmailHQL(@Param("email")String email);

	// delete employee using
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Employee WHERE email = ?1", nativeQuery = true)
	int deleteByEmailNative(@Param("email")String email);

}
