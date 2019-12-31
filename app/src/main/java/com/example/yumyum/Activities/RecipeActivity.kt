package com.example.yumyum.Activities

import android.content.Intent
import com.example.yumyum.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_recipe.*


class RecipeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        setToolbar();

        btnNextRecipe.setOnClickListener {
            onClick();
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item?.itemId) {
            android.R.id.home -> {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setToolbar(){
        // Get toolbar and set this as SupportActionbar.
        toolbar = findViewById(R.id.toolbarRecipe);
        setSupportActionBar(toolbar);

        // Set back arrow and title.
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    private fun onClick() {

        // Go from RecipeActivity to IngredientsActivity.
        val intent = Intent(this@RecipeActivity, IngredientsActivity::class.java);
        startActivity(intent);

        // Animation to fade into the IngredientsActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
