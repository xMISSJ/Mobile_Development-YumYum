package com.example.yumyum.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yumyum.Ingredient.Ingredient
import com.example.yumyum.Ingredient.IngredientsAdapter
import com.example.yumyum.R
import kotlinx.android.synthetic.main.activity_ingredients.*


class IngredientsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view : View;

    private val ingredientList = arrayListOf<Ingredient>();
    private val ingredientAdapter = IngredientsAdapter(ingredientList);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        setToolbar();
        initViews();

        btnAddIngredient.setOnClickListener {
            onIngredientAdd();
        }

        btnDone.setOnClickListener {
            onNextClick();
        }
    }

    private fun onIngredientAdd() {

        //TODO: Perhaps activate this function on keyboard enter press.
        //TODO: Retrieve the extra from RecipeActivity. Use this retrieved extra to send to InstructionsActivity.

        hideKeyboard(this.rvIngredients);

        // Check whether the input is not blank.
        if (etAddIngredient.text.toString().isNotBlank()){

            val ingredient = Ingredient("• ", etAddIngredient.text.toString());

            // Add this ingredient to the list. This list will now be shown in adapter and thus in the RecyclerView.
            ingredientList.add(ingredient);
            ingredientAdapter.notifyDataSetChanged();

            // Input field is cleared after.
            etAddIngredient.text?.clear();
        } else {
            Toast.makeText(this, "Field is empty.", Toast.LENGTH_LONG).show();
        }
    }

    private fun initViews() {
        rvIngredients.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvIngredients.adapter = ingredientAdapter;

        createItemTouchHelper().attachToRecyclerView(rvIngredients);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish();
                // Fading animation when returning to previous activity.
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onNextClick() {

        // Check whether RecyclerView has items.
        if (ingredientAdapter.itemCount != 0) {
            val intent = Intent(this@IngredientsActivity, InstructionsActivity::class.java);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please fill in at least one ingredient.", Toast.LENGTH_LONG).show();
        }

        // Fading animation when going from IngredientsActivity to InstructionsActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun setToolbar() {
        view = findViewById(R.id.parentIngredients);
        toolbar = view.findViewById(R.id.toolbarIngredients);
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
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
                ingredientList.removeAt(position);
                ingredientAdapter.notifyDataSetChanged();
            }
        }
        return ItemTouchHelper(callback)
    }

    // Hide keyboard function.
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0);
    }

}
