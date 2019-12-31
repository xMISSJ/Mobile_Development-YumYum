package com.example.yumyum.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.yumyum.R
import kotlinx.android.synthetic.main.activity_instructions.*

class InstructionsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private lateinit var view : View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        setToolbar();

        btnDone.setOnClickListener {
            onClick();
        }
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

    private fun setToolbar() {
        view = findViewById(R.id.parentInstructions);
        toolbar = view.findViewById(R.id.toolbarInstructions);
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    private fun onClick() {
        // Go to the RecipeActivity.
    }

}
