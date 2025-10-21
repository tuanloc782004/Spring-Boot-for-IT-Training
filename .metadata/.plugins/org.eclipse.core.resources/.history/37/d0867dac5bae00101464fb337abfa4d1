package com.example.dao;

import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // Đánh dấu đây là một Bean chuyên xử lý dữ liệu
public class StudentDAO {

	@Autowired // Spring sẽ tự động tiêm (inject) JdbcTemplate Bean vào đây
	private JdbcTemplate jdbcTemplate;

	// Phương thức thêm mới một sinh viên
	public void addStudent(Student student) {
		String sql = "INSERT INTO students (name, email, age) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getAge());
	}

	// Phương thức lấy tất cả sinh viên
	public List<Student> getAllStudents() {
		String sql = "SELECT * FROM students";
		return jdbcTemplate.query(sql, new StudentRowMapper());
	}

	// Lớp nội (inner class) để map một dòng trong database thành một đối tượng
	// Student
	private static final class StudentRowMapper implements RowMapper<Student> {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setEmail(rs.getString("email"));
			student.setAge(rs.getInt("age"));
			return student;
		}
	}
}