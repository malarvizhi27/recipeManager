package com.recipe.recipemanager.controller;

import com.recipe.recipemanager.Helper;
import com.recipe.recipemanager.model.Recipe;
import com.recipe.recipemanager.service.RecipeService;
import com.recipe.recipemanager.controller.RecipeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;


@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    void givenNoFilterCriteria_whenGetRecipes_thenReturnAllRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        Mockito.when(recipeService.getRecipes()).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.getRecipes();
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIdOfRecipe_whenGetRecipeById_thenReturnRecipeForProvidedId() {
        Recipe expectedResponse = Helper.createRecipe();
        Mockito.when(recipeService.getRecipeById(0)).thenReturn(expectedResponse);
        Recipe actualResponse = recipeController.getRecipeById(0);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenRecipe_whenAddRecipe_ThenReturnNewlyCreatedRecipe() {
        Recipe expectedResponse = Helper.createRecipe();
        Mockito.when(recipeService.saveRecipe(expectedResponse)).thenReturn(expectedResponse);
        Recipe actualResponse = recipeController.addRecipe(expectedResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenListOfRecipe_whenAddRecipes_ThenReturnNewlyCreatedRecipeList() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        Mockito.when(recipeService.saveRecipes(expectedResponse)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.addRecipes(expectedResponse);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIdOfRecipe_whenDeleteRecipe_ThenReturnNothing() {
        Recipe expectedResponse = Helper.createRecipe();
        recipeService.deleteRecipe(0);
        recipeController.deleteRecipe(0);
        Assertions.assertTrue(true);
    }

    @Test
    void givenUpdatedRecipe_whenUpdateRecipe_ThenUpdateRecipeParameters() {
        Recipe expectedResponse = Helper.createRecipe();
        Mockito.when(recipeService.updateRecipe(expectedResponse)).thenReturn(expectedResponse);
        Recipe actualResponse = recipeController.updateRecipe(expectedResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIsVegetarianTrue_whenFindRecipeIsVegetarian_ThenReturnListOfVegetarianRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        Mockito.when(recipeService.findByIsVegetarianEqualsOrderByTitleAsc(true)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeIsVegetarian(true);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenNoOfServing_whenFindRecipeByNoOfServing_ThenReturnListOfFilteredRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        Mockito.when(recipeService.findByNoOfServingEqualsOrderById(2)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeByNoOfServing(2);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIncludeAndExcludeIngredients_whenFindRecipeByIngredientsIncludesOrExcludes_ThenReturnListOfFilteredRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        List<String> includes = Arrays.asList("Butter");
        List<String> excludes = Arrays.asList("Cooking Oil");
        Mockito.when(recipeService.findByIngredientsNameIsInNameIsNotInOrderByTitle(includes, excludes)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeByIngredientsIncludesOrExcludes(includes, excludes);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenTextAsfilterCriteria_whenFindRecipeByInstructionContainsText_ThenReturnListOfFilteredRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        String textToSearch = "Preheat oven";
        Mockito.when(recipeService.findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(textToSearch)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeByInstructionContainsText(textToSearch);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}