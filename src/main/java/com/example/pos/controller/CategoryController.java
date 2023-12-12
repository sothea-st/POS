package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Category;
import com.example.pos.service.CategoryService;
import jakarta.validation.Valid;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// import java.util.ArrayList;
// import java.util.Map;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/category")
@Validated
public class CategoryController {
	@Autowired
	private CategoryService service;

	@PostMapping
	public ResponseEntity<?> saveCategory(@Valid @ModelAttribute Category c) {
		Category data = service.saveCategory(c);

		return JavaResponse.success(data);
	}

	@GetMapping
	public ResponseEntity<?> getCategory() {
		ArrayList<Category> data = service.getCategory();
		return JavaResponse.success(data);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @Valid @RequestBody Category category) {
		Category data = service.updateCategory(id, category);
		return JavaResponse.success(data);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") int id) {
		Category data = service.getCategoryById(id);
		return JavaResponse.success(data);
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id, @RequestParam("status") int status) {
		service.deleteCategory(id, status);
		return JavaResponse.success("delete success");
	}

}
