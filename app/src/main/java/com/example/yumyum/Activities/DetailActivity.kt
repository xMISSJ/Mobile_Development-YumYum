package com.example.yumyum.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yumyum.Detail.DetailIngredient
import com.example.yumyum.Detail.DetailIngredientAdapter
import com.example.yumyum.Ingredient.Ingredient
import com.example.yumyum.Instruction.DetailInstructionAdapter
import com.example.yumyum.Instruction.Instruction
import com.example.yumyum.R
import com.example.yumyum.Room.RecipeRepository
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view: View;

    private val detailInstructionsList = arrayListOf<Instruction>();
    private val detailIngredientsList = arrayListOf<Ingredient>()
    private val detailInstructionAdapter = DetailInstructionAdapter(detailInstructionsList);
    private val detailIngredientAdapter = DetailIngredientAdapter(detailIngredientsList);

    private lateinit var recipeRepository: RecipeRepository;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setToolbar();
        initViews();
        getRecipesFromDatabase();
    }

    private fun getRecipesFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val recipes = withContext(Dispatchers.IO) {
                recipeRepository.getAllRecipes();
            }
            this@DetailActivity.detailIngredientsList.clear();

            // Every recipe item.
            for (iterator in recipes.indices) {
                // For everything in ingredientsList in recipe.
                for (index in recipes[iterator].ingredients!!.indices) {
                    //this@DetailActivity.detailIngredientsList.addAll(recipes[iterator].ingredients[index]);
                }
            }

            this@DetailActivity.detailIngredientsList.clear();
            this@DetailActivity.detailInstructionsList.clear();

            this@DetailActivity.detailIngredientsList.clear();
            //this@DetailActivity.detailInstructionsList.addAll();

            this@DetailActivity.detailIngredientAdapter.notifyDataSetChanged();
            this@DetailActivity.detailInstructionAdapter.notifyDataSetChanged();
        }
    }

    private fun initViews() {
        rvDetailIngredients.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDetailIngredients.adapter = detailIngredientAdapter;

        rvDetailInstructions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDetailInstructions.adapter = detailInstructionAdapter;
    }

    private fun setToolbar() {
        view = findViewById(R.id.parentDetail);
        toolbar = view.findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
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
}
