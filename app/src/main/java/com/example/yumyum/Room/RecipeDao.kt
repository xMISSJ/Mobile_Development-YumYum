package com.example.yumyum.Room

import androidx.room.*
import com.example.yumyum.Recipe.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    suspend fun getAllRecipes(): List<Recipe>;

    @Insert
    suspend fun insertRecipe(recipe: Recipe);

    @Delete
    suspend fun deleteRecipe(recipe: Recipe);

    @Update
    suspend fun updateRecipe(recipe: Recipe);
}