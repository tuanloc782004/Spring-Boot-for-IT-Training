package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration: Đánh dấu class này là một lớp cấu hình. Spring sẽ quét class
 *                 này để tìm các phương thức @Bean.
 */
@Configuration
public class AppConfig {

	/**
	 * @Bean: Đặt trên một phương thức. Báo cho Spring: 1. "Hãy chạy phương thức
	 *        này." 2. "Đối tượng mà phương thức này trả về (ở đây là ModelMapper)
	 *        sẽ được đăng ký như một Bean trong IoC Container."
	 *
	 *        Đây là cách để đưa các đối tượng từ thư viện bên thứ 3 (hoặc các class
	 *        phức tạp) vào sự quản lý của Spring.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
