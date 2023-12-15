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
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PathVariable;


 
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

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id, @RequestParam("status") boolean status,@RequestParam("isDeleted") boolean isDeleted) {
		service.deleteCategory(id, status,isDeleted);
		return JavaResponse.success("delete success");
	}

 

}
