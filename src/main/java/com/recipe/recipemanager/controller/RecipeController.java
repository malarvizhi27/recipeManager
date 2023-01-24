package com.recipe.recipemanager.controller;

import com.recipe.recipemanager.model.Recipe;
import com.recipe.recipemanager.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;


/**
 * Recipe management
 * This Class provides REST End point to handle web requests
 *
 */
@RestController
@RequestMapping("recipe-manager/rest/api/v1/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Rest End Point to get Recipes
     * @return Recipes
     */
    @GetMapping(value = "/getRecipes")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    /**
     * Rest End Point to get Recipe
     * @param id
     * @return Recipe
     */
    @GetMapping("/getRecipeById/{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    /**
     * Rest End Point to add new Recipe
     * @param recipe
     * @return Recipe
     */
    @PostMapping(value = "/addRecipe", consumes = "application/json", produces = "application/json")
    public Recipe addRecipe(@RequestBody @Valid Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }

    /**
     * Rest End Point to add new Recipes
     * @param recipes
     * @return Recipes
     */
    @PostMapping("/addRecipes")
    public List<Recipe> addRecipes(@RequestBody @Valid List<Recipe> recipes) {
        return recipeService.saveRecipes(recipes);
    }

    /**
     * Rest End Point to delete existing Recipe
     * @param id
     */
    @DeleteMapping("/deleteRecipe/{id}")
    public void deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
    }

    /**
     * Rest End Point to update Recipe
     * @param recipe
     * @return Recipe
     */
    @PutMapping("/updateRecipe")
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        return recipeService.updateRecipe(recipe);
    }

    /**
     * Rest End Point to identify if teh recipe is veg
     * @param isVegetarian
     * @return Recipes
     */
    @GetMapping("/search/findRecipeIsVegetarian/{isVegetarian}")
    public List<Recipe> findRecipeIsVegetarian(@PathVariable boolean isVegetarian) {
        return recipeService.findByIsVegetarianEqualsOrderByTitleAsc(isVegetarian);
    }

    /**
     * Rest End Point to identify recipe by no of servings
     * @param noOfServing
     * @return Recipes
     */
    @GetMapping("/search/findRecipeByNoOfServing/{noOfServing}")
    public List<Recipe> findRecipeByNoOfServing(@PathVariable int noOfServing) {
        return recipeService.findByNoOfServingEqualsOrderById(noOfServing);
    }
    /**
     * Rest End Point to identify recipe with including and excluding particular ingredient
     * @param includes and excluded
     * @return Recipe
     */
    @GetMapping("/search/findRecipeByIngredientsIncludesOrExcludes/")
    public List<Recipe> findRecipeByIngredientsIncludesOrExcludes(@RequestParam @Valid Collection<String> includes, @RequestParam @Valid Collection<String> excludes) {
        return recipeService.findByIngredientsNameIsInNameIsNotInOrderByTitle(includes, excludes);
    }

    /**
     * Rest End Point to find recipe based on the instructions
     * @param text
     * @return Recipe
     */
    @GetMapping("/search/findRecipeByInstructionContainsText/{text}")
    public List<Recipe> findRecipeByInstructionContainsText(@PathVariable String text) {
        return recipeService.findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(text);
    }
}
