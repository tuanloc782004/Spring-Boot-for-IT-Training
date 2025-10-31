package com.example.demo.controller;

import com.example.demo.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	// --- Database Tạm thời (In-memory) ---
	private final List<Employee> employeeList = new ArrayList<>();
	// Tạo một biến đếm ID tự tăng
	private final AtomicLong counter = new AtomicLong();

	// Khởi tạo một vài dữ liệu mẫu
	public EmployeeController() {
		employeeList.add(new Employee(counter.incrementAndGet(), "Tuan Loc", "tuanloc@sun.com", "IT"));
		employeeList.add(new Employee(counter.incrementAndGet(), "Anh Vu", "anhvu@sun.com", "HR"));
	}

	// --- Nhiệm vụ 1: Tạo API lấy danh sách nhân viên ---
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	// --- Nhiệm vụ 2: Tạo API thêm nhân viên mới ---
	/**
	 * @RequestBody Employee newEmployee: Báo cho Spring Boot biết: "Hãy lấy dữ liệu
	 *              JSON từ body của request và tự động chuyển đổi nó thành một đối
	 *              tượng Employee tên là newEmployee".
	 */
	@PostMapping
	public Employee createEmployee(@RequestBody Employee newEmployee) {
		// Gán một ID mới cho nhân viên
		newEmployee.setId(counter.incrementAndGet());

		// Thêm nhân viên mới vào danh sách (database tạm)
		employeeList.add(newEmployee);

		// Trả về đối tượng nhân viên vừa tạo (đã có ID)
		return newEmployee;
	}

	// --- Cách nâng cao hơn của Nhiệm vụ 2 (Sử dụng ResponseEntity) ---
	/**
	 * Cách này tốt hơn vì chúng ta có thể kiểm soát mã trạng thái HTTP. Khi tạo mới
	 * thành công, chúng ta nên trả về mã 201 CREATED.
	 *
	 * Bỏ comment ở dưới và comment hàm createEmployee() ở trên để thử.
	 */
	/*
	 * @PostMapping public ResponseEntity<Employee>
	 * createEmployeeAdvanced(@RequestBody Employee newEmployee) {
	 * newEmployee.setId(counter.incrementAndGet()); employeeList.add(newEmployee);
	 * 
	 * // Trả về mã 201 CREATED và đối tượng vừa tạo trong body return new
	 * ResponseEntity<>(newEmployee, HttpStatus.CREATED); }
	 */
}
