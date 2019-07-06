package com.kotlin.guesstheword.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kotlin.guesstheword.R
import com.kotlin.guesstheword.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.title_fragment, container, false)

        binding.playGameButton.setOnClickListener {
            // SOS: an easier way to call findNavController. Note that I can only call this on a
            // fragment that is inside a NavHostFragment, like all my fragments are
            findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
        }

        return binding.root
    }
}
