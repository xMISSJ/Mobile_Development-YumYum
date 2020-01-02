package com.example.yumyum.Activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.example.yumyum.R
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_recipe.*

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

        if (etRecipeName.text.isNotBlank() && etServings.text.isNotBlank()) {
            var recipeName = etRecipeName.text.toString();
            var recipeImage = profileImageUri;
            var servings = etServings.text.toString();

            // Go from RecipeActivity to IngredientsActivity.
            val intent = Intent(this@RecipeActivity, IngredientsActivity::class.java);

            // Put the variables as extra to send them to the IngredientsActivity.
            intent.putExtra("RECIPE_NAME", recipeName);
            intent.putExtra("RECIPE_IMAGE", recipeImage.toString()); // Convert Uri to String.
            intent.putExtra("RECIPE_SERVINGS", servings);
            startActivity(intent);
        } else {
            Toast.makeText(this, "One of the fields or multiple are empty.", Toast.LENGTH_LONG).show();
        }
        // Animation to fade into the IngredientsActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun onPictureClick() {
        val intent = Intent(Intent.ACTION_PICK);

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
