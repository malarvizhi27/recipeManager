package com.recipe.recipemanager.dao;

import com.recipe.recipemanager.model.Ingredient;
import com.recipe.recipemanager.model.Recipe;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>, JpaSpecificationExecutor<Recipe> {
    List<Recipe> findByIsVegetarianEqualsOrderByTitleAsc(boolean isVegetarian);

    List<Recipe> findByNoOfServingEqualsOrderById(int noOfServing);

    @Query("SELECT r FROM Recipe r JOIN r.ingredients i where i.name IN (?1) AND i.name NOT IN (?2)")
    List<Recipe> findByIngredientsNameIsInNameIsNotInOrderByTitle(Collection<String> includes,
                                                                  Collection<String> excludes);

    List<Recipe> findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(String instructions);


}