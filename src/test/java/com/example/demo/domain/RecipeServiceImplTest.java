package com.example.demo.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.repositories.RecipeRepository;
import com.example.demo.services.RecipeServiceImpl;
import com.fasterxml.jackson.databind.Module.SetupContext;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Before
	public void Setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void getRecipes() {

		Recipe recipe = new Recipe();
		Set<Recipe> recipes = recipeService.getRecipes();
		recipes.add(recipe);
		
		when(recipeService.getRecipes()).thenReturn(recipes);
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}

}
