package com.recipe.recipemanager.exception;

public class RecipeNotFoundException extends ResourceNotFoundException{

    public RecipeNotFoundException(){
    }

    public RecipeNotFoundException(String message){
        super(message);
    }

}
