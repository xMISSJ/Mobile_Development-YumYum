package com.example.yumyum.Activities

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.yumyum.R
import kotlinx.android.synthetic.main.activity_recipe.*
import java.io.ByteArrayOutputStream
import java.io.InputStream


const val PROFILE_PICTURE_REQUEST_CODE = 100;

class RecipeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar;
    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        setToolbar();

        btnAddImage.setOnClickListener {
            onPictureClick();
        }

        btnNextRecipe.setOnClickListener {
            onClick();
        }

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


    private fun setToolbar() {
        // Get toolbar and set this as SupportActionbar.
        toolbar = findViewById(R.id.toolbarRecipe);
        setSupportActionBar(toolbar);

        // Set back arrow and title.
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    private fun onClick() {

        //TODO: Perhaps activate this function on keyboard enter press.

        if (etRecipeName.text.isNotBlank() && etServings.text.isNotBlank() && etPreparationTime.text.isNotBlank()) {
            val recipeName = etRecipeName.text.toString();
            val recipeImage = profileImageUri;
            val servings = etServings.text.toString();
            val preparationTime = etPreparationTime.text.toString();

            // Go from RecipeActivity to IngredientsActivity.
            val nextIntent = Intent(this@RecipeActivity, IngredientsActivity::class.java);

            nextIntent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT;

            // Put the variables as extra to send them to the IngredientsActivity.
            nextIntent.putExtra("RECIPE_NAME", recipeName);
            nextIntent.putExtra("RECIPE_IMAGE", recipeImage.toString()); // Convert Uri to String.
            nextIntent.putExtra("RECIPE_SERVINGS", servings);
            nextIntent.putExtra("RECIPE_PREPARATION_TIME", preparationTime);

            startActivity(nextIntent);
            finish();
        } else {
            Toast.makeText(this, "One of the fields or multiple are empty.", Toast.LENGTH_LONG).show();
        }
        // Animation to fade into the IngredientsActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun onPictureClick() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Sets the type as image/*. This ensures only components of type image are selected.
        intent.type = "image/*";

        startActivityForResult(intent, PROFILE_PICTURE_REQUEST_CODE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        // If operation has succeeded.
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PROFILE_PICTURE_REQUEST_CODE -> {
                    profileImageUri = data?.data;
                    ivProfilePicture.setImageURI(profileImageUri);
                }
            }
        }
    }
}
