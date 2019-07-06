package com.kotlin.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kotlin.guesstheword.R
import com.kotlin.guesstheword.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.score_fragment, container, false
        )

        val score = ScoreFragmentArgs.fromBundle(arguments!!).score

        viewModelFactory = ScoreViewModelFactory(score)

        // SOS: The reason we use a factory here is cause we want to get a ViewModel initialized w
        // some value (the score). TBH, Idk why we have to use a viewModel here. Things work exactly
        // the same w/o it, cause ScoreFragmentArgs are persistent anyway
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ScoreViewModel::class.java)

        binding.scoreText.text = viewModel.score.toString()

        return binding.root
    }
}
