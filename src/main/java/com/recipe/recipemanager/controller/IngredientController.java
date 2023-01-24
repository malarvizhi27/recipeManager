package com.recipe.recipemanager.controller;

import com.recipe.recipemanager.model.Ingredient;
import com.recipe.recipemanager.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ingredient management
 * This Class provides REST End point to handle web requests
 *
 */
@RestController
@RequestMapping("recipe-manager/rest/api/v1/ingredient")
public class IngredientController {
    final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * Rest End Point to get Ingredients
     * @return List of Ingredients
     */
    @GetMapping("/getIngredients")
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    /**
     * Rest End Point to get Ingredients based on ID
     * @param  id
     * @return Ingredient
     */
    @GetMapping("/getIngredientById/{id}")
    public Ingredient getIngredientById(@PathVariable int id) {
        return ingredientService.getIngredientById(id);
    }

    /**
     * Rest End Point to add new Ingredient
     * @param ingredient with id and name
     * @return Ingredient
     */
    @PostMapping("/addIngredient")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    /**
     * Rest End Point to add new Ingredients
     * @param  ingredients with id and name
     * @return Ingredient List
     */
    @PostMapping("/addIngredients")
    public List<Ingredient> addIngredients(@RequestBody List<Ingredient> ingredients) {
        return ingredientService.saveIngredients(ingredients);
    }
    /**
     * Rest End Point to delete Ingredient
     * @param   id
     */
    @DeleteMapping("/deleteIngredient/{id}")
    public void deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
    }

    /**
     * Rest End Point to update Ingredient
     * @param  ingredient
     * @return Ingredient
     */
    @PutMapping("/updateIngredient")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(ingredient);
    }

}
