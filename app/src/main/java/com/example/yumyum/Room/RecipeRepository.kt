package com.example.yumyum.Room

import android.content.Context
import com.example.yumyum.Recipe.Recipe

class RecipeRepository (context: Context) {

    private val recipeDao : RecipeDao

    init {
        val recipeRoomDatabase = RecipeRoomDatabase.getDatabase(context);
        recipeDao = recipeRoomDatabase!!.recipeDao();
    }

    suspend fun getAllRecipes() : List<Recipe> = recipeDao.getAllRecipes();
    suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe);
    suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe);
    suspend fun deleteAllRecipes() = recipeDao.deleteAllRecipes();
}