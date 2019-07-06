package com.kotlin.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kotlin.guesstheword.R
import com.kotlin.guesstheword.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.score_fragment, container, false
        )

        val score = ScoreFragmentArgs.fromBundle(arguments!!).score

        val viewModelFactory = ScoreViewModelFactory(score)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = this

        binding.playAgainButton.setOnClickListener {
            findNavController().navigate(ScoreFragmentDirections.actionScoreToGame())
        }

        return binding.root
    }
}
