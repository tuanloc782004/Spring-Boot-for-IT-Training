package com.example.demo.controller;

import com.example.demo.dto.DepartmentStatsDTO;
import com.example.demo.model.Employee;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ReportService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
public class EmployeeViewController {

	private final EmployeeService employeeService;
	private final DepartmentService departmentService;
	private final ReportService reportService;

	public EmployeeViewController(EmployeeService employeeService, DepartmentService departmentService,
			ReportService reportService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
		this.reportService = reportService;
	}

	@GetMapping("/list")
	public String showEmployeeList(Model model, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long departmentId) {

		List<Employee> employeeList = employeeService.searchEmployees(name, departmentId);

		model.addAttribute("employees", employeeList);

		model.addAttribute("allDepartments", departmentService.getAllDepartments());

		model.addAttribute("searchName", name);
		model.addAttribute("searchDeptId", departmentId);

		return "employee-list";
	}

	@GetMapping("/add")
	public String showAddEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());

		model.addAttribute("allDepartments", departmentService.getAllDepartments());

		return "employee-form";
	}

	@PostMapping("/add")
	public String addEmployee(@Valid @ModelAttribute("employee") Employee employee, Model model) {

		employeeService.createEmployee(employee);

		return "redirect:/employees/list";
	}

	@GetMapping("/statistics")
	public String showStatisticsPage(Model model) {

		List<DepartmentStatsDTO> departmentStats = reportService.getEmployeeCountByDepartment();
		long totalEmployees = reportService.getTotalEmployeeCount();

		model.addAttribute("departmentStats", departmentStats);
		model.addAttribute("totalEmployees", totalEmployees);

		// Trả về tên file "statistics.html"
		return "statistics";
	}

}