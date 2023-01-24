package com.recipe.recipemanager.service;

import com.recipe.recipemanager.model.Recipe;

import java.util.Collection;
import java.util.List;

/**
 *
 * This Interface contains method for Recipe Service
 *
 */
public interface RecipeService {
    Recipe saveRecipe(Recipe recipe);

    List<Recipe> saveRecipes(List<Recipe> recipes);

    List<Recipe> getRecipes();

    Recipe getRecipeById(int id);

    void deleteRecipe(int id);

    Recipe updateRecipe(Recipe recipe);

    List<Recipe> findByIsVegetarianEqualsOrderByTitleAsc(boolean isVegetarian);

    List<Recipe> findByNoOfServingEqualsOrderById(int noOfServing);

    List<Recipe> findByIngredientsNameIsInNameIsNotInOrderByTitle(Collection<String> includes,
                                                                  Collection<String> excludes);

    List<Recipe> findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(String text);
}
