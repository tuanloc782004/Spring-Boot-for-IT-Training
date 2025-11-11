package com.example.demo.controller;

import com.example.demo.dto.DepartmentStatsDTO;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/statistics")
	public Map<String, Object> getFullStatistics() {
		List<DepartmentStatsDTO> departmentStats = reportService.getEmployeeCountByDepartment();
		long totalEmployees = reportService.getTotalEmployeeCount();

		Map<String, Object> response = new HashMap<>();
		response.put("totalEmployees", totalEmployees);
		response.put("employeesByDepartment", departmentStats);

		return response;
	}
}