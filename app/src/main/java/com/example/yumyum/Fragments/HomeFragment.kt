package com.example.yumyum.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yumyum.Activities.HomeActivity

import com.example.yumyum.R
import com.example.yumyum.Recipe.Recipe
import com.example.yumyum.Recipe.RecipesAdapter
import com.example.yumyum.Room.RecipeRepository
import kotlinx.android.synthetic.main.content_home.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private val recipesList = arrayListOf<Recipe>();
    private val recipesAdapter = RecipesAdapter(recipesList);

    private lateinit var myView: View;
    private lateinit var recipeRepository: RecipeRepository;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_home, container, false);
        recipeRepository = RecipeRepository(activity!!.applicationContext);
        return myView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        myView.rootView.ivRecipeBook.setImageResource(R.drawable.recipe_book_small);

        initViews();

        getGamesFromDatabase();
    }

    private fun initViews() {
        rvRecipes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rvRecipes.adapter = recipesAdapter;

        createItemTouchHelper().attachToRecyclerView(rvRecipes);
    }

    private fun getGamesFromDatabase() {
        Toast.makeText(context, "HOI", Toast.LENGTH_LONG).show();
        CoroutineScope(Dispatchers.Main).launch {
            val recipes = withContext(Dispatchers.IO) {
                recipeRepository.getAllRecipes();
            }

            this@HomeFragment.recipesList.clear();
            this@HomeFragment.recipesList.addAll(recipes);
            this@HomeFragment.recipesAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false;
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val recipeToDelete = recipesList[position];

                // Delete recipe at this position.
                deleteRecipe(recipeToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun deleteRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                recipeRepository.deleteRecipe(recipe)
            }
            getGamesFromDatabase()
        }
    }

}
