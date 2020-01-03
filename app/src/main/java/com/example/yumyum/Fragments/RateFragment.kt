package com.example.yumyum.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yumyum.R
import kotlinx.android.synthetic.main.content_home.view.*
import kotlinx.android.synthetic.main.fragment_rate.*


class RateFragment : Fragment() {

    private lateinit var myView: View;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_rate, container, false);
        return myView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        myView.rootView.ivRecipeBook.setImageResource(R.drawable.rating_star);
        var btnSubmit = myView.rootView.findViewById<Button>(R.id.btnRate);

        // If clicked on submit.
        btnSubmit.setOnClickListener {
            onSubmitClick();
        }

        var ratingBar = myView.rootView.findViewById<RatingBar>(R.id.rbRate);

        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { rBar: RatingBar, rating: Float, fromUser: Boolean ->
                onRatingClick(ratingBar);
            }

    }

    private fun onSubmitClick() {
        //TODO: Switch to ratedFragment. Make this fragment.
        Toast.makeText(context, "Go to Fragment.", Toast.LENGTH_LONG).show();
    }

    private fun onRatingClick(ratingBar: RatingBar) {

        var ratingComment = myView.rootView.findViewById<TextView>(R.id.tvRatingComment);

        val commentsList = arrayListOf(
            "No rating...",
            "Terrible...",
            "Bad.",
            "Still bad.",
            "So-so.",
            "Could be better.",
            "Quite good!",
            "Nice!",
            "Cool!",
            "I love it!",
            "Just perfect!!"
        );

        when (ratingBar.rating.toString()) {
            "0.0" -> ratingComment.text = commentsList[0];
            "0.5" -> ratingComment.text = commentsList[1];
            "1.0" -> ratingComment.text = commentsList[2];
            "1.5" -> ratingComment.text = commentsList[3];
            "2.0" -> ratingComment.text = commentsList[4];
            "2.5" -> ratingComment.text = commentsList[5];
            "3.0" -> ratingComment.text = commentsList[6];
            "3.5" -> ratingComment.text = commentsList[7];
            "4.0" -> ratingComment.text = commentsList[8];
            "4.5" -> ratingComment.text = commentsList[9];
            "5.0" -> ratingComment.text = commentsList[10];
        }
    }

}
