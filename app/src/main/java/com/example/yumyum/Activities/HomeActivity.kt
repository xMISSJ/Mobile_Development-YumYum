package com.example.yumyum.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.default_toolbar.*
import com.example.yumyum.R
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView;
    private lateinit var toolbar: Toolbar;
    private lateinit var view: View;

    private lateinit var favoriteImage: ImageView;
    private var favorite = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivProfileImage.setOnClickListener {
            onProfileImageClick();
        }

        btnAddRecipe.setOnClickListener {
            onAddClick();
        }
/*
        ivFavorite.setOnClickListener {
            onFavoriteClick();
        }*/

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
        startActivity(intent);

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

/*    private fun onFavoriteClick(){
        // Recipe is favorited.
        favorite = true;

        // Change the image as feedback.
        ivFavorite.setImageResource(R.drawable.ic_favorited);
    }*/
}

