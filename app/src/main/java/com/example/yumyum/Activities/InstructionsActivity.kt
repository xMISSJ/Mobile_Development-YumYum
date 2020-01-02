package com.example.yumyum.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yumyum.Ingredient.Ingredient
import com.example.yumyum.Instruction.Instruction
import com.example.yumyum.Instruction.InstructionsAdapter
import com.example.yumyum.R
import kotlinx.android.synthetic.main.activity_ingredients.*
import kotlinx.android.synthetic.main.activity_instructions.*
import kotlinx.android.synthetic.main.activity_instructions.btnDone

class InstructionsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view: View;

    private val instructionList = arrayListOf<Instruction>();
    private val instructionAdapter = InstructionsAdapter(instructionList);

    private var recipeName: String? = null;
    private var recipeImage: Uri? = null;
    private var recipeServings: String? = null;

    private var recipeIngredientsList = arrayListOf<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        btnAddInstruction.setOnClickListener {
            onAddClick();
        }

        btnDone.setOnClickListener {
            onDoneClick();
        }

        setToolbar();
        initViews();
        retrieveExtras();
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

    private fun setToolbar() {
        view = findViewById(R.id.parentInstructions);
        toolbar = view.findViewById(R.id.toolbarInstructions);
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
                instructionList.removeAt(position);
                instructionAdapter.notifyDataSetChanged();
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun onAddClick() {

        //TODO: User can only put the steps numerical -> Step 1, step 2... || not Step 1, step 3.

        hideKeyboard(this.rvInstructions);

        // Check whether the texts are not empty.
        if (etAddStep.text.toString().isNotBlank() && etAddInstruction.text.toString().isNotBlank()) {

            val stepText = etAddStep.text.toString();

            val instruction =
                Instruction("Step ${stepText}.", etAddInstruction.text.toString());

            // Add this ingredient to the list. This list will now be shown in adapter and thus in the RecyclerView.
            instructionList.add(instruction);
            instructionAdapter.notifyDataSetChanged();

            // Input field is cleared after.
            etAddStep.text?.clear();
            etAddInstruction.text?.clear();
        } else {
            Toast.makeText(this, "One or more of the input fields are empty.", Toast.LENGTH_LONG)
                .show();
        }
    }

    private fun onDoneClick() {

        // Check whether RecyclerView has items.
        if (instructionAdapter.itemCount != 0) {
            val intent = Intent(this@InstructionsActivity, HomeActivity::class.java);

            // Put the variables as extra to send them to the IngredientsActivity.
            // They all have a "2" added to not confuse names with RecipeActivity.
            intent.putExtra("RECIPE_NAME3", recipeName);
            intent.putExtra("RECIPE_IMAGE3", recipeImage);
            intent.putExtra("RECIPE_SERVINGS3", recipeServings);

            // Add extras from this activity.
            intent.putExtra("RECIPE_INGREDIENTS_LIST3", recipeIngredientsList);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please fill in at least one ingredient.", Toast.LENGTH_LONG)
                .show();
        }

        //Fading animation when going from InstructionsActivity to HomeActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun initViews() {
        rvInstructions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvInstructions.adapter = instructionAdapter;

        createItemTouchHelper().attachToRecyclerView(rvIngredients);
    }

    private fun retrieveExtras() {
        // Retrieve the extra from IngredientsActivity. Use this retrieved extra to send to HomeActivity.
        recipeName = intent.getStringExtra("RECIPE_NAME2");
        recipeImage = Uri.parse(intent.getStringExtra("RECIPE_IMAGE2"));
        recipeServings = intent.getStringExtra("RECIPE_SERVINGS2");

        recipeIngredientsList = intent.getStringArrayListExtra("RECIPE_INGREDIENTS_LIST2");
    }

    // Hide keyboard function.
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0);
    }
}
