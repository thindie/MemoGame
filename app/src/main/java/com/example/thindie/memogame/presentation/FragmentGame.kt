package com.example.thindie.memogame.presentation

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thindie.memogame.databinding.FragmentGameBinding

class FragmentGame : Fragment() {


    private lateinit var viewModel: FragmentGameViewModel
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var showTime : Int = 0
    private var waitTime : Int = 0


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

        viewModel.showTime.observe(viewLifecycleOwner){
            showTime = it
        }

        viewModel.waitTime.observe(viewLifecycleOwner){
            waitTime = it
        }

        viewModel.question.observe(viewLifecycleOwner) {
            showThisRound(it)
        }
        viewModel.time.observe(viewLifecycleOwner) {
            binding.tvTime.text = it
        }
    }

    private fun showThisRound(it: MutableList<Int>?) {

        val listOfTV: MutableList<TextView> = mutableListOf()
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
        val listOfColors = it ?: throw RuntimeException("Empty list of colors")

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
            showTime *  TIMER_TICK,
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
        var count = 0
        for (i in 0 until listOfTV.size) {
                listOfTV[i].setTextColor(Color.BLUE)
                listOfTV[i].setBackgroundColor(Color.BLUE)
                listOfTV[i].setOnClickListener {
                    val tv = it as TextView
                    if(!tv.text.equals(ANSWER)){
                        Toast.makeText(requireContext(),"bad", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        count++
                        if(count == NEEDED_ANSWERS){
                            Toast.makeText(requireContext(),"cool", Toast.LENGTH_SHORT).show()
                            viewModel.askQuestion()
                        }
                    }
                }

            object : CountDownTimer(
                waitTime *  TIMER_TICK,
                TIMER_TICK
            ) {

                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {

                }
            }.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val TIMER_TICK = 1000L
        private const val NEEDED_ANSWERS = 2
        private const val ANSWER = "x"
        private const val CLEARED_STRING_FIELD = ""
    }

}