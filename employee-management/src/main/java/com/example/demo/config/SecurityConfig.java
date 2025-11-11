package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // Bật tính năng bảo mật web
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Cấu hình phân quyền (Authorization)
            .authorizeHttpRequests(authorize -> authorize
                // Cho phép tất cả mọi người truy cập trang đăng ký, CSS, JS
                .requestMatchers("/register", "/css/**", "/js/**").permitAll()

                // --- Phân quyền cho API (REST) ---
                // USER chỉ được GET
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("USER", "ADMIN")
                // ADMIN được POST, PUT, DELETE
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")

                // --- Phân quyền cho Web (Thymeleaf) ---
                // USER chỉ được xem trang list
                .requestMatchers("/employees/list").hasAnyRole("USER", "ADMIN")
                // ADMIN được vào trang add, edit, delete
                .requestMatchers("/employees/add").hasRole("ADMIN")
                .requestMatchers("/employees/edit/**").hasRole("ADMIN")
                .requestMatchers("/employees/delete/**").hasRole("ADMIN")

                // Bất kỳ request nào khác đều cần phải đăng nhập
                .anyRequest().authenticated()
            )
            // 2. Cấu hình trang đăng nhập (Form Login)
            .formLogin(form -> form
                .loginPage("/login") // Chỉ định URL của trang đăng nhập custom (nếu có)
                .defaultSuccessUrl("/employees/list", true) // Trang chuyển đến sau khi login thành công
                .permitAll() // Cho phép tất cả mọi người truy cập trang login
            )
            // 3. Cấu hình Đăng xuất (Logout)
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") // Trang chuyển đến sau khi logout
                .permitAll()
            )
            // 4. (Tùy chọn) Bật Basic Auth để test API bằng Postman
            .httpBasic(withDefaults());

        return http.build();
    }
}