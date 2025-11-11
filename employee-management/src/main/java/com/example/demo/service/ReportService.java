package com.example.demo.service;

import com.example.demo.dto.DepartmentStatsDTO;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService; // Dùng lại hàm count() đã cache

	// Nhiệm vụ 1: Thống kê theo phòng ban
	public List<DepartmentStatsDTO> getEmployeeCountByDepartment() {
		return employeeRepository.countEmployeesByDepartment();
	}

	// Nhiệm vụ 2: Thống kê tổng số nhân viên
	public long getTotalEmployeeCount() {
		return employeeService.countEmployees();
	}
}