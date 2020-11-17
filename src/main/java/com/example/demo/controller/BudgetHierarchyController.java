package com.example.demo.controller;

import com.example.demo.domain.Structure;
import com.example.demo.services.BudgetHierarchyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BudgetHierarchyController {

	private final BudgetHierarchyService budgetHierarchyService;
	
	public BudgetHierarchyController(BudgetHierarchyService budgetHierarchyService) {
		this.budgetHierarchyService = budgetHierarchyService;
	}

	@PostMapping("/api/budget-amount")
	public ResponseEntity initialBudgetAmount() {
		budgetHierarchyService.initialBudgetHierarchy();

		ResponseEntity result = new ResponseEntity(HttpStatus.CREATED);
		return result;
	}

	@GetMapping("/api/budget-amount")
	public ResponseEntity initialBudgetAmountGet() {
		Iterable<Structure> structures = budgetHierarchyService.initialBudgetAmountGet();

		ResponseEntity result = new ResponseEntity(structures, HttpStatus.OK);
		return result;
	}

	@PutMapping("/api/budget-amount")
	public ResponseEntity updateBudgetAmountFunds(@RequestParam Long planingId, @RequestParam String code) {
		budgetHierarchyService.updateFunds(planingId, code);

		ResponseEntity result = new ResponseEntity(HttpStatus.OK);
		return result;
	}
}
