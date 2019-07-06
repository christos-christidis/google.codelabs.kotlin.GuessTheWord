package com.kotlin.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val tag = GameViewModel::class.java.simpleName

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    // SOS: map takes a LiveData and returns another LiveData that is to be observed
    private val _currentTime = MutableLiveData<Long>()
    val currentTimeString: LiveData<String> = Transformations.map(_currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    // SOS: this is the way Kotlin implements static things...
    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L
    }

    private val timer: CountDownTimer

    private lateinit var wordList: MutableList<String>

    init {
        _word.value = ""
        _score.value = 0

        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }
        }

        timer.start()

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
        if (wordList.isEmpty()) {
            onGameFinish()
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        if (wordList.isNotEmpty()) {
            _score.value = score.value?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (wordList.isNotEmpty()) {
            _score.value = score.value?.plus(1)
        }
        nextWord()
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag, "GameViewModel destroyed!")
        timer.cancel()
    }
}