package com.example.thindie.memogame.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thindie.memogame.R
import com.example.thindie.memogame.databinding.FragmentStartBinding
import kotlinx.coroutines.*

class FragmentStart : Fragment() {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coroutineScope.launch {
            startAnimation()
        }
        binding.buStartGame.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentStart_to_fragmentGame)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
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
                delay(300)
            }
            list.forEach {
                it.setBackgroundColor(Color.BLUE)
                list.shuffle()
                delay(300)
            }
        }
    }

}