package com.example.demo.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.example.demo.services.RecipeService;

public class IndexControllerTest {

	@Mock
	private RecipeService recipeService;
	@Mock
	Model model;
	IndexController indexController;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
		
	}

	@Test
	public void test() {

		String viewName = indexController.getIndexPage(model);
		
		assertEquals("index", viewName);
		
		verify(recipeService, times(1)).getRecipes();
	//	verify(model, times(1)).addAttribute(eq("recipes", anySet()));
	
	}

}
