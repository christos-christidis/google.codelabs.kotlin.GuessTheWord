package com.kotlin.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

    private val tag = ScoreViewModel::class.java.simpleName

    var score = finalScore

    init {
        Log.i(tag, "Final score = $finalScore")
    }
}