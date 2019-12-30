package com.example.yumyum.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yumyum.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.default_toolbar.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivProfileImage.setOnClickListener {
            onProfileImageClick();
        }

        btnAddRecipe.setOnClickListener {
            onAddClick();
        }
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
}
