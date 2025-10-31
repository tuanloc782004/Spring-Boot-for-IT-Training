package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * @Service: Đây là một annotation (chú thích) đặc biệt. 1. Nó là một dạng
 *           của @Component, báo cho Spring biết: "Hãy quét (scan) class này và
 *           tạo ra một đối tượng (Bean) của nó". 2. Spring sẽ quản lý đối tượng
 *           này trong "IoC Container" (thùng chứa). 3. Nó cũng đánh dấu về mặt
 *           ngữ nghĩa đây là một class Tầng Dịch vụ (Service Layer).
 */
@Service
public class UtilityService {

	/**
	 * Một phương thức ví dụ để sinh mã nhân viên ngẫu nhiên.
	 */
	public String generateEmployeeCode() {
		// Lấy 8 ký tự đầu của một UUID ngẫu nhiên
		return "EMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}
}
