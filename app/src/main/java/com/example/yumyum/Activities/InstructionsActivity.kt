package com.example.yumyum.Activities

import android.content.Intent
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

        //TODO: use "Step${tvStep.text.toString()}." for the step.
    }


    //TODO: Use this commented code to recycle HomeActivity, instead of making a new instance.
/*    override fun onSupportNavigateUp(): Boolean {

        // Go from InstructionsActivity to HomeActivity.
        val intent = Intent(this@InstructionsActivity, HomeActivity::class.java);

        // If the activity already exists: instead of launching a new instance of this activity, all of the other activities on top of it will be closed.
        // his Intent will be delivered to the (now on top) old activity as a new Intent.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        return true;
    }*/

}
