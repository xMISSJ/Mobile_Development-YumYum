package com.example.yumyum.ViewModel_LiveData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yumyum.Recipe.Recipe
import com.example.yumyum.Room.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeneralViewModel (application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO);
    private val recipeRepository = RecipeRepository(application.applicationContext);

    val recipes: LiveData<List<Recipe>> = recipeRepository.getAllRecipes();

    fun insertRecipe(recipe: Recipe) {
        ioScope.launch {
            recipeRepository.insertRecipe(recipe);
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        ioScope.launch {
            recipeRepository.deleteRecipe(recipe);
        }
    }

    fun deleteAllRecipes() {
        ioScope.launch {
            recipeRepository.deleteAllRecipes();
        }
    }
}