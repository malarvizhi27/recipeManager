package com.recipe.recipemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.recipemanager.Helper;
import com.recipe.recipemanager.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class IngredientControllerTest {
    static String INGREDIENT_MANAGER_SERVICE = "/recipe-manager/rest/api/v1/ingredient/";

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void given_whenGetIngredients_thenReturnIngredient() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(INGREDIENT_MANAGER_SERVICE + "/getIngredients")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").value(Matchers.hasSize(25)))
                .andReturn();
    }

    @Test
    void given_whenGetIngredientById_thenReturnIngredient() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(INGREDIENT_MANAGER_SERVICE + "/getIngredientById/102")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(102))
                .andReturn();
    }

    @Test
    void givenIngredient_whenAddIngredient_thenReturnIngredient() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(INGREDIENT_MANAGER_SERVICE + "/addIngredient")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(Helper.createIngredient()));
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andReturn();
    }

    @Test
    void givenIngredientList_whenAddIngredients_thenReturnIngredientList() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(INGREDIENT_MANAGER_SERVICE + "/addIngredients")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(Helper.createIngredients()));
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void givenId_whenDeleteIngredient_thenReturnNoValue() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete(INGREDIENT_MANAGER_SERVICE + "/deleteIngredient/103")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andReturn();
    }

    @Test
    void givenIngredient_whenUpdateIngredient_thenReturnIngredient() throws Exception {
        Ingredient updatedIngredient = Ingredient.builder().id(106).name("Black Pepper").build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put(INGREDIENT_MANAGER_SERVICE + "/updateIngredient")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedIngredient));
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(106))
                .andReturn();
    }
}
