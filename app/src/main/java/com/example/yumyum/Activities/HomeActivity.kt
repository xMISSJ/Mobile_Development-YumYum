package com.example.yumyum.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.yumyum.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.default_toolbar.*


const val REQUEST_CODE = 100;

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView;
    private lateinit var toolbar: Toolbar;
    private lateinit var view: View;

    private var fragment: Fragment? = null;
    private var bundle: Bundle? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragment = supportFragmentManager.findFragmentById(R.id.homeFragment);

        ivProfileImage.setOnClickListener {
            onProfileImageClick();
        }

        btnAddRecipe.setOnClickListener {
            onAddClick();
        }

        setToolbar();
        initNavigation();
    }

    private fun onProfileImageClick() {
        // From HomeActivity to ProfileActivity.
        val intent = Intent(this@HomeActivity, ProfileActivity::class.java);
        startActivity(intent);

        // Animation to fade into the ProfileActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun onAddClick() {
        // From HomeActivity to AddActivity.
        val intent = Intent(this@HomeActivity, RecipeActivity::class.java);
        startActivityForResult(intent, REQUEST_CODE);

        // Animation to fade into the AddActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun setToolbar() {

        bottomNavigation = findViewById(R.id.navView);
        view = findViewById(R.id.parentMain);
        toolbar = view.findViewById(R.id.toolbarHome);

        setSupportActionBar(toolbar);
    }

    private fun initNavigation() {
        // The NavController.
        val navController = findNavController(R.id.navHostFragment);

        // Connect the NavController with the BottomNavigationView.
        NavigationUI.setupWithNavController(navView, navController);

        // Connect the navHostFragment with the Toolbar.
        val appBarConfiguration = AppBarConfiguration(navController.graph);

        // Automatically handles the back button.
        // toolbar.setupWithNavController(navController, appBarConfiguration);
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE -> {
                    val recipeName = data?.extras?.getString("RECIPE_NAME");
                    val recipeImage = data?.extras?.getString("RECIPE_IMAGE");
                    val recipeServings = data?.extras?.getString("RECIPE_SERVINGS");
                    val recipePreparationTime = data?.extras?.getString("RECIPE_PREPARATION_TIME");
                    val recipeIngredients = data?.extras?.getStringArrayList("RECIPE_INGREDIENTS_LIST");
                    val recipeInstructions = data?.extras?.getStringArrayList("RECIPE_INSTRUCTIONS_LIST");

                    bundle = intent.extras;
                    bundle?.putString("RECIPE_NAME", recipeName);
                    bundle?.putString("RECIPE_IMAGE", recipeImage);
                    bundle?.putString("RECIPE_SERVINGS", recipeServings);
                    bundle?.putString("RECIPE_PREPARATION_TIME", recipePreparationTime);
                    bundle?.putStringArrayList("RECIPE_INGREDIENTS_LIST", recipeIngredients);
                    bundle?.putStringArrayList("RECIPE_INSTRUCTIONS_LIST", recipeInstructions);

                    // Send the bundle to the HomeFragment.kt.
                    fragment?.arguments = bundle;
                }
            }
        }
    }

    fun getBundle(): Bundle? {
        return bundle;
    }

}

