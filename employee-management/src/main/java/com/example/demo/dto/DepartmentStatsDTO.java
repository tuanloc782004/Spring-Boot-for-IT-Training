package com.example.demo.dto;

// DTO này dùng để chứa kết quả của truy vấn GROUP BY
public class DepartmentStatsDTO {
    private String departmentName;
    private Long employeeCount;

    // BẮT BUỘC: Tạo constructor nhận các trường
    // JPA @Query sẽ dùng constructor này để tạo đối tượng
    public DepartmentStatsDTO(String departmentName, Long employeeCount) {
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
    }

    // --- Tạo Getters (và Setters nếu cần) ---
    public String getDepartmentName() {
        return departmentName;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }
}