package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Category;
import com.example.pos.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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

	@GetMapping("/parentId/{parentId}")
	public ResponseEntity<?> getCategory(@PathVariable("parentId") int parentId) {
		ArrayList<Category> data = service.getCategory(parentId);
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

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id ,@RequestBody Category c){
		service.deleteCategory(id,c);
		return JavaResponse.deleteSuccess(id);
	}

}
