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
import com.example.thindie.memogame.databinding.FragmentGameBinding

class FragmentGame : Fragment() {


    private lateinit var viewModel: FragmentGameViewModel
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var showTime: Int = 0
    private var waitTime: Int = 0
    private lateinit var listOfTV: MutableList<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FragmentGameViewModel::class.java]

    }

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
        viewModel = ViewModelProvider(this)[FragmentGameViewModel::class.java]

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
            if (color == Color.CYAN) {
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

                } else {
                    count++
                    if (count == NEEDED_ANSWERS) {
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
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoseGame() {
        listOfTV.forEach {
            it.setTextColor(Color.RED)
            it.setBackgroundColor(Color.RED)
            it.isClickable = false
            it.isFocusable = false
        }
    }

    companion object {
        private const val TIMER_TICK = 1000L
        private const val NEEDED_ANSWERS = 2
        private const val ANSWER = "x"
        private const val CLEARED_STRING_FIELD = ""
    }

}