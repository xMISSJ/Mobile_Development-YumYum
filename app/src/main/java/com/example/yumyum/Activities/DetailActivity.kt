package com.example.yumyum.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yumyum.Detail.DetailIngredientAdapter
import com.example.yumyum.Ingredient.Ingredient
import com.example.yumyum.Instruction.DetailInstructionAdapter
import com.example.yumyum.Instruction.Instruction
import com.example.yumyum.R
import com.example.yumyum.ViewModel_LiveData.GeneralViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.add_toolbar.view.*

class DetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view: View;

    private val detailInstructionsList = arrayListOf<Instruction>();
    private val detailIngredientsList = arrayListOf<Ingredient>()
    private val detailInstructionAdapter = DetailInstructionAdapter(detailInstructionsList);
    private val detailIngredientAdapter = DetailIngredientAdapter(detailIngredientsList);

    private lateinit var viewModel: GeneralViewModel;

    private var recipeId: Long? = null;
    private var recipeName: String? = null;
    private var recipeImage: Int? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recipeId = intent.getLongExtra("ID", 0);
        recipeName = intent.getStringExtra("NAME");
        recipeImage = intent.getIntExtra("IMAGE", 0);

        viewModel = ViewModelProviders.of(this).get(GeneralViewModel::class.java);

        getRecipesFromDatabase();

        initViews();
        setToolbar();
    }

    private fun getRecipesFromDatabase() {
        // Observe recipes from the view model, update the list when the data is changed.
        viewModel.recipes.observe(this, Observer { recipes ->
            this@DetailActivity.detailInstructionsList.clear();
            this@DetailActivity.detailIngredientsList.clear();

            // Every recipe item.
            for (iterator in recipes.indices) {
                if (recipes[iterator].id == recipeId) {
                    // For everything in ingredientsList in recipe.
                    for (index in recipes[iterator].ingredients!!) {
                        this@DetailActivity.detailIngredientsList.add(index);
                    }

                    for (index in recipes[iterator].instructions!!) {
                        this@DetailActivity.detailInstructionsList.add(index);
                    }

                    recipeName = recipes[iterator].name;
                }
            }

            this@DetailActivity.detailIngredientAdapter.notifyDataSetChanged();
            this@DetailActivity.detailInstructionAdapter.notifyDataSetChanged();
        })
    }

    private fun initViews() {
        rvDetailIngredients.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDetailIngredients.adapter = detailIngredientAdapter;

        rvDetailInstructions.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDetailInstructions.adapter = detailInstructionAdapter;
    }

    private fun setToolbar() {
        view = findViewById(R.id.parentDetail);
        toolbar = view.findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        toolbar.tvTitle.text = recipeName;
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
