package com.example.yumyum.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.yumyum.R
import kotlinx.android.synthetic.main.activity_ingredients.*

class IngredientsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view : View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        btnDone.setOnClickListener {
            onClick();
        }

        setToolbar();
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

    private fun onClick() {
        val intent = Intent(this@IngredientsActivity, InstructionsActivity::class.java);
        startActivity(intent);

        // Fading animation when going from IngredientsActivity to InstructionsActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun setToolbar() {
        view = findViewById(R.id.parentIngredients);
        toolbar = view.findViewById(R.id.toolbarIngredients);
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }
}
