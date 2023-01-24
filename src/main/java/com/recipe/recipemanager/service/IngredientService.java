package com.recipe.recipemanager.service;

import com.recipe.recipemanager.model.Ingredient;

import java.util.List;

/**
 *
 * This Interface contains method for Ingredient service
 *
 */
public interface IngredientService {

    public Ingredient saveIngredient(Ingredient ingredient);

    public List<Ingredient> saveIngredients(List<Ingredient> ingredients);

    public List<Ingredient> getIngredients();

    public Ingredient getIngredientById(int id);

    public void deleteIngredient(int id);

    public Ingredient updateIngredient(Ingredient ingredient);
}
