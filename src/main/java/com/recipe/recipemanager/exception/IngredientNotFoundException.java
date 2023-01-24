package com.recipe.recipemanager.exception;

public class IngredientNotFoundException extends ResourceNotFoundException{

    public IngredientNotFoundException(){
    }

    public IngredientNotFoundException(String message){
        super(message);
    }
}
