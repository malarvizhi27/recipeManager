package com.recipe.recipemanager.service.impl;

import com.recipe.recipemanager.dao.IngredientRepository;
import com.recipe.recipemanager.exception.IngredientNotFoundException;
import com.recipe.recipemanager.exception.RecipeNotFoundException;
import com.recipe.recipemanager.model.Ingredient;
import com.recipe.recipemanager.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is an implementation of Ingredient Service
 *
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    /**
     * This is the Method returns Ingredient
     *
     * @param ingredient
     * @return - Ingredient
     */
    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    /**
     * This is the Method returns List of Ingredient
     *
     * @param ingredients
     * @return - List of Ingredients
     */
    @Override
    public List<Ingredient> saveIngredients(List<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(int id) {
        if (ingredientRepository.findById(id).isEmpty())
            throw new IngredientNotFoundException();
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteIngredient(int id) {
        if (ingredientRepository.findById(id).isPresent())
            ingredientRepository.deleteById(id);
        throw new RecipeNotFoundException();
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient existingIngredient = ingredientRepository.findById(ingredient.getId()).orElseThrow(IngredientNotFoundException::new);
        existingIngredient.setName(ingredient.getName());
        return ingredientRepository.save(existingIngredient);
    }
}
