package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	// --- CREATE ---
	@Transactional
	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

	// --- READ ---
	@Transactional(readOnly = true)
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
	}

	// --- UPDATE ---
	@Transactional
	public Author updateAuthor(Long id, Author authorDetails) {
		Author author = getAuthorById(id);
		author.setName(authorDetails.getName());
		author.setEmail(authorDetails.getEmail());
		return authorRepository.save(author);
	}

	// --- DELETE ---
	@Transactional
	public void deleteAuthor(Long id) {
		authorRepository.deleteById(id);
	}
}