package com.example.yumyum.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yumyum.Recipe.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    // LiveData: The generated code runs the query asynchronously on a background thread when needed.
    fun getAllRecipes(): LiveData<List<Recipe>>;

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes();

    @Insert
    suspend fun insertRecipe(recipe: Recipe);

    @Delete
    suspend fun deleteRecipe(recipe: Recipe);
}