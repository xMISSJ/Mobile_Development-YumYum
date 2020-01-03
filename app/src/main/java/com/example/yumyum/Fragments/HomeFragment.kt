package com.example.yumyum.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yumyum.Activities.HomeActivity
import com.example.yumyum.Activities.REQUEST_CODE
import com.example.yumyum.Activities.RecipeActivity

import com.example.yumyum.R
import com.example.yumyum.Recipe.Recipe
import com.example.yumyum.Recipe.RecipesAdapter
import kotlinx.android.synthetic.main.content_home.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val recipesList = arrayListOf<Recipe>();
    private val recipesAdapter = RecipesAdapter(recipesList);

    private lateinit var myView: View;

    private var recipeName: String? = null;
    private var recipeImage: String? = null;
    private var recipeServings: String? = null;
    private var recipePreparationTime: String? = null;

    private lateinit var addButton: Button;

    private var done: Boolean? = false;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_home, container, false);

        return myView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        myView.rootView.ivRecipeBook.setImageResource(R.drawable.recipe_book_small);

        initViews();

        if(done == true) {
            Toast.makeText(context, "Done", Toast.LENGTH_LONG).show();
            // Check whether all the user input data has been received.
            setData();
        }

    }

    private fun initViews() {
        rvRecipes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rvRecipes.adapter = recipesAdapter;

        createItemTouchHelper().attachToRecyclerView(rvRecipes);
    }

    private fun setData() {
        val recipe = Recipe(
            name = recipeName,
            image = recipeImage?.toUri(),
            servings = recipeServings?.toInt(),
            preparationTime = recipePreparationTime?.toInt(),
            ingredients = null,
            instructions = null
        )

        recipesList.add(recipe);
        recipesAdapter.notifyDataSetChanged();
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
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                // Delete ingredient at this position.
                recipesList.removeAt(position);
                recipesAdapter.notifyDataSetChanged();
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun convertArray(): ArrayList<String> {
        val elements = arrayListOf<String>();
        return elements
    }

}
