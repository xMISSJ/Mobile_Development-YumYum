package com.example.yumyum.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.yumyum.R

class DetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view: View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setToolbar();
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
