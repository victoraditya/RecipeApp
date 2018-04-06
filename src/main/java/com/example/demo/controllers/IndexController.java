package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.UnitOfMeasure;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.UnitOfMeasureRepository;
import com.example.demo.services.RecipeService;

@Controller
public class IndexController {
	
	private final RecipeService recipeService;

	/*private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;*/

	/*@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {
		Optional<Category> categoryOptional = categoryRepository.findByDescription("INDIAN"); 
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("TEASPOON");
		System.out.println("Cat id is : "+categoryOptional.get().getId());
		System.out.println("UOM id is : "+unitOfMeasureOptional.get().getId());
		return "index";
	}*/
	
	@RequestMapping({"","/","/index"})
	public String getIndexPage(Model model) {
		System.out.println("---------------- inside getIndexPage() ----------------");
		model.addAttribute("recipes",recipeService.getRecipes());
		return "index";
	}

	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	/*public String getAllRecipes(Model model) {
		model.addAttribute("recipes", recipeService.get)
	}*/

	/*public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}*/

	

}
