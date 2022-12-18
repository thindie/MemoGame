package com.example.thindie.memogame.presentation

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thindie.memogame.R
import com.example.thindie.memogame.databinding.FragmentFinishBinding
import com.example.thindie.memogame.domain.entities.GameResult
import kotlinx.coroutines.*

class FragmentFinish : Fragment() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var _binding: FragmentFinishBinding? = null
    private val binding get() = _binding!!
    private lateinit var gameResult : GameResult

    override fun onAttach(context: Context) {
        super.onAttach(context)
        gameResult = requireArguments()
            .getParcelable<GameResult>(GAME_RESULT) as GameResult
    }

   private fun setTextWithScores(){
        binding.tvResult.text = String.format("Ваш результат %d", gameResult.score)
    }

    private fun setButtonListeners(){
        binding.buShowScores.setOnClickListener {
            findNavController()
                .navigate(R.id.action_fragmentFinish_to_fragmentRecord2)
        }
        binding.buStartGame.setOnClickListener {
            findNavController()
                .navigate(R.id.action_fragmentFinish_to_fragmentGame)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextWithScores()
        setButtonListeners()
        coroutineScope.launch {
            startAnimation()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        _binding = null
    }

    private suspend fun startAnimation() {
        val list: MutableList<TextView> = mutableListOf()
        list.add(binding.tvOpt1)
        list.add(binding.tvOpt2)
        list.add(binding.tvOpt3)
        list.add(binding.tvOpt4)
        while (true) {
            list.forEach {
                it.setBackgroundColor(Color.RED)
                list.shuffle()
                delay(500)
            }
            list.forEach {
                it.setBackgroundColor(resources.getColor(R.color.purple_700))
                list.shuffle()
                delay(500)
            }
        }
    }

    companion object {
        private const val GAME_RESULT = "gameResult"
    }
}