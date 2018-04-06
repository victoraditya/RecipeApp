package com.example.demo.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Category;
import com.example.demo.domain.Difficulty;
import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Notes;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.UnitOfMeasure;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.RecipeRepository;
import com.example.demo.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository measureRepository;

	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository measureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.measureRepository = measureRepository;
	}

	private List<Recipe> getRecipes()
	{

		List<Recipe> recipes = new ArrayList<>(2);

		Optional<UnitOfMeasure> eachUomOptional = measureRepository.findByDescription("EACH");

		if (!eachUomOptional.isPresent())
			throw new RuntimeException("Expected UOM not found!!");

		Optional<UnitOfMeasure> tableSpoonUomOptional = measureRepository.findByDescription("TABLESPOON");

		if (!tableSpoonUomOptional.isPresent())
			throw new RuntimeException("Expected UOM not found!!");

		Optional<UnitOfMeasure> teaSpoonUomOptional = measureRepository.findByDescription("TEASPOON");

		if (!teaSpoonUomOptional.isPresent())
			throw new RuntimeException("Expected UOM not found!!");

		Optional<UnitOfMeasure> dashUomOptional = measureRepository.findByDescription("DASH");

		if (!dashUomOptional.isPresent())
			throw new RuntimeException("Expected UOM not found!!");

		Optional<UnitOfMeasure> pintUomOptional = measureRepository.findByDescription("PINT");

		if (!pintUomOptional.isPresent())
			throw new RuntimeException("Expected UOM not found!!");

		Optional<UnitOfMeasure> cupsUomOptional = measureRepository.findByDescription("CUP");

		if (!cupsUomOptional.isPresent())
			throw new RuntimeException("Expected UOM not found!!");
		
	//	UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupsUom = cupsUomOptional.get();
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("AMERICAN");
		if(!americanCategoryOptional.isPresent())
			throw new RuntimeException("Expected Category not foung!!");
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("MEXICAN");
		if(!mexicanCategoryOptional.isPresent())
			throw new RuntimeException("Expected Category not found!!");
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirections("Some nice description for Guac!!");
		
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("Some relevent recipe notes for Guac! yummy!!");
		guacRecipe.setNotes(guacNotes);
		
		guacRecipe.getIngredient().add(new Ingredient("Ancho Chilli Powder", new BigDecimal(2), tableSpoonUom));
		guacRecipe.getIngredient().add(new Ingredient("Dreid Oregano", new BigDecimal(1), teaSpoonUom));
		
		recipes.add(guacRecipe);
		
		return recipes;
		
		
		
		
		
		
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		recipeRepository.saveAll(getRecipes());
	}

}
