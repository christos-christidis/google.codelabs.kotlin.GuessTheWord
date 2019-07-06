package com.kotlin.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val tag = GameViewModel::class.java.simpleName

    var word = ""
    var score = 0
    private lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        Log.i(tag, "GameViewModel created!")
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen", "hospital", "basketball", "cat", "change", "snail", "soup", "calendar",
                "sad", "desk", "guitar", "home", "railway", "zebra", "jelly", "car", "crow",
                "trade", "bag", "roll", "bubble")
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            word = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        if (wordList.isNotEmpty()) {
            score--
        }
        nextWord()
    }

    fun onCorrect() {
        if (wordList.isNotEmpty()) {
            score++
        }
        nextWord()
    }

    // SOS: viewModel is destroyed when fragment is destroyed BUT NOT FOR A CONFIG CHANGE!
    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "GameViewModel destroyed!")
    }
}