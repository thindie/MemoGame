package com.example.thindie.memogame.presentation

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thindie.memogame.R
import com.example.thindie.memogame.databinding.FragmentGameBinding
import com.example.thindie.memogame.domain.entities.GameResult

class FragmentGame : Fragment() {


    private val viewModel: FragmentGameViewModel by lazy {
         ViewModelProvider(this)[FragmentGameViewModel::class.java]
    }
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var showTime: Int = INITIAL_COUNT
    private var waitTime: Int = INITIAL_COUNT
    private var answersNeeded: Int = INITIAL_COUNT
    private lateinit var listOfTV: MutableList<TextView>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startShowGame()
    }

    private fun startShowGame() {


        viewModel.showTime.observe(viewLifecycleOwner) {
            showTime = it
        }
        viewModel.waitTime.observe(viewLifecycleOwner) {
            waitTime = it
        }
        viewModel.question.observe(viewLifecycleOwner) {
            showThisRound(it)
        }
        viewModel.time.observe(viewLifecycleOwner) {
            binding.tvTime.text = it
        }
        viewModel.score.observe(viewLifecycleOwner) {
            binding.tvScore.text = it
        }

        viewModel.recordResult.observe(viewLifecycleOwner) {
            yesIsaRecord(it)
        }

        viewModel.noRecordResult.observe(viewLifecycleOwner) {
            noIsNoRecord(it)
        }

        viewModel.answersNeeded.observe(viewLifecycleOwner) {
             answersNeeded = it
        }


    }

    private fun showThisRound(colors: MutableList<Int>?) {

        listOfTV = mutableListOf()
        with(binding) {
            listOfTV.add(tvOpt1)
            listOfTV.add(tvOpt2)
            listOfTV.add(tvOpt3)
            listOfTV.add(tvOpt4)
            listOfTV.add(tvOpt5)
            listOfTV.add(tvOpt6)
            listOfTV.add(tvOpt7)
            listOfTV.add(tvOpt8)
            listOfTV.add(tvOpt9)
            listOfTV.add(tvOpt10)
            listOfTV.add(tvOpt11)
            listOfTV.add(tvOpt12)
            listOfTV.add(tvOpt13)
            listOfTV.add(tvOpt14)
            listOfTV.add(tvOpt15)
            listOfTV.add(tvOpt16)
        }
        val listOfColors = colors ?: throw RuntimeException("Empty list of colors")

        for (i in 0 until listOfTV.size) {
            val color = listOfColors[i]
            if (color == Color.RED) {
                listOfTV[i].setTextColor(color)
                listOfTV[i].text = ANSWER

            }
            if (color == Color.BLUE) {
                listOfTV[i].setTextColor(color)
                listOfTV[i].text = CLEARED_STRING_FIELD
            }
            listOfTV[i].setBackgroundColor(color)

        }

        object : CountDownTimer(
            showTime * TIMER_TICK,
            TIMER_TICK
        ) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                solveThisRound(listOfTV)
            }
        }.start()
    }

    private fun solveThisRound(listOfTV: MutableList<TextView>) {
        var timer: CountDownTimer? = null
        var count = 0

        for (i in 0 until listOfTV.size) {
            listOfTV[i].setTextColor(Color.BLUE)
            listOfTV[i].setBackgroundColor(Color.BLUE)
            listOfTV[i].setOnClickListener {
                val tv = it as TextView
                if (!tv.text.equals(ANSWER)) {
                    showLoseGame()
                    viewModel.isARecordGame()
                } else {
                    tv.text = CLEARED_STRING_FIELD
                    count++
                    if (count == answersNeeded) {
                        timer?.cancel()
                        viewModel.collectScore()
                        viewModel.askQuestion()
                    }

                }
            }
        }
        timer = object : CountDownTimer(
            waitTime * TIMER_TICK,
            TIMER_TICK
        ) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                showLoseGame()
                viewModel.isARecordGame()
            }
        }.start()
    }

    private fun yesIsaRecord(gameResult: GameResult) {
        findNavController().navigate(
            R.id.action_fragmentGame_to_fragmentWriteWinner,
            Bundle().apply {
                putParcelable(
                    GAME_RESULT, gameResult
                )
            }
        )
    }

    private fun noIsNoRecord(gameResult: GameResult) {
        findNavController().navigate(
            R.id.action_fragmentGame_to_fragmentFinish,
            Bundle().apply {
                putParcelable(
                    GAME_RESULT, gameResult
                )
            }
        )
    }

    private fun showLoseGame() {
        listOfTV.forEach {
            it.setTextColor(Color.RED)
            it.setBackgroundColor(Color.RED)
            it.isClickable = false
            it.isFocusable = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val GAME_RESULT = "gameResult"
        private const val TIMER_TICK = 1000L
        private const val INITIAL_COUNT = 0
        private const val ANSWER = "x"
        private const val CLEARED_STRING_FIELD = ""
    }

}