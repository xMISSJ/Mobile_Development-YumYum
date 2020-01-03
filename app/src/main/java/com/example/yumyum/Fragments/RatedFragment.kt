package com.example.yumyum.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.example.yumyum.R
import kotlinx.android.synthetic.main.fragment_rated.*

class RatedFragment : Fragment() {

    private val args: RatedFragmentArgs by navArgs();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rated, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        tvThankYou.text = "Thank you for rating us with ${args.rating} stars. We really value your feedback!";
    }

}
