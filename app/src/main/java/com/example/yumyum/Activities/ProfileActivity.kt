package com.example.yumyum.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.yumyum.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view: View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setToolbar();
    }

    private fun setToolbar() {
        view = findViewById(R.id.parentProfile);
        toolbar = view.findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId;
        return when (id) {
            android.R.id.home -> {
                finish();
                return true
            }
            else -> super.onOptionsItemSelected(item);
        }
    }
}
