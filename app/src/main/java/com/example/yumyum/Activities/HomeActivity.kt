package com.example.yumyum.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.yumyum.Fragments.HomeFragment
import com.example.yumyum.Fragments.RatingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.default_toolbar.*
import com.example.yumyum.R

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView;
    private lateinit var selectedFragment: Fragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivProfileImage.setOnClickListener {
            onProfileImageClick();
        }

        btnAddRecipe.setOnClickListener {
            onAddClick();
        }

        bottomNavigation = findViewById(R.id.nvBottom);

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
        val intent = Intent(this@HomeActivity, AddActivity::class.java);
        startActivity(intent);

        // Animation to fade into the AddActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun initNavigation() {
        // The NavController.
        val navController = findNavController(R.id.navHostFragment);

        // Connect the NavController with the BottomNavigationView.
        NavigationUI.setupWithNavController(nvBottom, navController);

        // Connect the navHostFragment with the Toolbar.
        val appBarConfiguration = AppBarConfiguration(navController.graph);

        // Add a Destination Changed Listener. This gets called whenever the navigation controller is navigating to another fragment.
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment -> HomeFragment();
                R.id.rateFragment -> RatingFragment();
            }
        }

    }
}

