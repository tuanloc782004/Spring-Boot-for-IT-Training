package com.example.demo.controller;

import com.example.demo.service.UtilityService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController: Đánh dấu class này là một Controller chuyên xử lý các
 *                  request API và tự động chuyển đổi kết quả trả về (ví dụ:
 *                  String) thành dạng JSON hoặc Text.
 */
@RestController
public class HelloController {

	private final UtilityService utilityService;
	private final ModelMapper modelMapper;

	public HelloController(UtilityService utilityService, ModelMapper modelMapper) {
		this.utilityService = utilityService;
		this.modelMapper = modelMapper;
	}

	/**
	 * @GetMapping("/hello"): Ánh xạ (map) các HTTP GET request đến đường dẫn
	 * "/hello" vào phương thức này.
	 */
	@GetMapping("/hello")
	public String sayHello() {
		String newEmployeeCode = utilityService.generateEmployeeCode();
		return "Hello World! Your new employee code is: " + newEmployeeCode;
	}

	@GetMapping("/check-bean")
	public String checkBean() {
		if (modelMapper != null) {
			return "ModelMapper bean has been successfully injected!";
		} else {
			return "ModelMapper bean is null.";
		}
	}
}