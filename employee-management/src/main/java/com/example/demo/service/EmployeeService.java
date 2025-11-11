package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	public Employee createEmployee(Employee employee) {
		log.info("Đang tạo nhân viên mới với email: {}", employee.getEmail());
		Employee savedEmployee = employeeRepository.save(employee);
		log.info("Đã tạo thành công nhân viên ID: {}", savedEmployee.getId());
		return savedEmployee;
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public List<Employee> searchEmployees(String name, Long departmentId) {
		if (name != null && !name.isEmpty()) {
			return employeeRepository.findByNameContaining(name);
		}
		if (departmentId != null) {
			return employeeRepository.findByDepartmentId(departmentId);
		}
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		// Dùng .orElseThrow() để ném Exception nếu không tìm thấy
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
	}

	@Transactional
	public Employee updateEmployee(Long id, Employee employeeDetails) {
		log.info("Đang cập nhật cho nhân viên ID: {}", id);
		Employee employee = getEmployeeById(id); // Dùng lại hàm get (đã có xử lý 404)

		employee.setName(employeeDetails.getName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setDepartment(employeeDetails.getDepartment());

		Employee updatedEmployee = employeeRepository.save(employee);
		log.info("Đã cập nhật thành công nhân viên ID: {}", updatedEmployee.getId());
		return updatedEmployee;
	}

	@Transactional
	public void deleteEmployee(Long id) {
		log.warn("Đang chuẩn bị xóa nhân viên ID: {}", id); // Dùng log.warn() cho hành động nguy hiểm
		Employee employee = getEmployeeById(id); // Kiểm tra tồn tại trước khi xóa
		employeeRepository.delete(employee);
		log.info("Đã xóa thành công nhân viên ID: {}", id);
	}

	/**
	 * Lấy tổng số nhân viên.
	 * 
	 * @Cacheable: Báo cho Spring biết kết quả của hàm này cần được cache.
	 *             "employeeCount": Là tên của vùng cache (phải khớp với tên bạn
	 *             định nghĩa trong application.yml).
	 */
	@Cacheable("employeeCount")
	public long countEmployees() {
		// Log này CHỈ xuất hiện khi hàm thực sự chạy (lần đầu, hoặc khi cache hết hạn)
		log.info("Đang thực hiện truy vấn COUNT() xuống Database...");
		return employeeRepository.count();
	}

}