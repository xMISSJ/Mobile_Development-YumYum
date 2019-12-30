package com.example.yumyum.Activities

import com.example.yumyum.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class AddActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setToolbar();

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
        toolbar = findViewById(R.id.toolbarAdd);
        setSupportActionBar(toolbar);

        // Set back arrow and title.
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }
}
