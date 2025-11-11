package com.example.demo.repository;

import com.example.demo.dto.DepartmentStatsDTO;
import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Tương đương: SELECT * FROM employees WHERE name LIKE %:name%
	List<Employee> findByNameContaining(String name);

	// Tương đương: SELECT * FROM employees WHERE department_id = :departmentId
	List<Employee> findByDepartmentId(Long departmentId);

	/**
	 * "SELECT new
	 * com.example.employeemanagement.dto.DepartmentStatsDTO(e.department.name,
	 * COUNT(e))" -> Gọi constructor của DTO mà chúng ta đã tạo
	 *
	 * "FROM Employee e GROUP BY e.department.name" -> Truy vấn từ Entity Employee,
	 * nhóm theo tên của phòng ban
	 */
	@Query("SELECT new com.example.demo.dto.DepartmentStatsDTO(e.department.name, COUNT(e)) "
			+ "FROM Employee e GROUP BY e.department.name")
	List<DepartmentStatsDTO> countEmployeesByDepartment();
	
}