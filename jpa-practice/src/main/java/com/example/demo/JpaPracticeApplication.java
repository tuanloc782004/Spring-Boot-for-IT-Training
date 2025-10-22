package com.example.demo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Author;
import com.example.demo.model.Post;
import com.example.demo.repository.AuthorRepository;

@SpringBootApplication
public class JpaPracticeApplication implements CommandLineRunner {

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaPracticeApplication.class, args);
	}

	// Phương thức này sẽ tự động chạy sau khi Spring khởi động
	@Override
	public void run(String... args) throws Exception {
		// --- Test CREATE và Relationship ---
		System.out.println("--- Đang tạo Author và Post ---");
		Author author = new Author();
		author.setName("Sun*");
		author.setEmail("hello@sun-asterisk.com");

		Post post1 = new Post();
		post1.setTitle("Giới thiệu Spring Data JPA");
		post1.setAuthor(author);

		Post post2 = new Post();
		post2.setTitle("Giới thiệu Repository");
		post2.setAuthor(author);

		author.setPosts(Set.of(post1, post2));

		authorRepository.save(author);

		// --- Test READ ---
		System.out.println("\n--- Đang đọc Author ---");
		Author savedAuthor = authorRepository.findById(author.getId()).get();
		System.out.println("Tìm thấy Author ID: " + savedAuthor.getId());

		// --- Test UPDATE ---
		System.out.println("\n--- Đang cập nhật Author ---");
		savedAuthor.setName("Sun* Updated");
		authorRepository.save(savedAuthor);
		System.out.println("Đã cập nhật Author: " + savedAuthor.getName());

		// --- Test DELETE ---
		System.out.println("\n--- Đang xóa Author ---");
		authorRepository.deleteById(savedAuthor.getId());
		System.out.println("Đã xóa Author. Tổng số Author còn lại: " + authorRepository.count());
	}

}
