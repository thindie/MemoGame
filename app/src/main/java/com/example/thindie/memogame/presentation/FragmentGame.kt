package com.example.thindie.memogame.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thindie.memogame.databinding.FragmentGameBinding

class FragmentGame : Fragment() {



    private lateinit var viewModel: FragmentGameViewModel
    private var _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FragmentGameViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startShowGame()
    }

    private fun startShowGame() {
       viewModel = ViewModelProvider(this)[FragmentGameViewModel::class.java]
        viewModel.question.observe(viewLifecycleOwner){

        }
        viewModel.time.observe(viewLifecycleOwner){
            binding.tvTime.text = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}