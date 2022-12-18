package com.example.thindie.memogame.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thindie.memogame.R
import com.example.thindie.memogame.databinding.FragmentWriteWinnerBinding
import com.example.thindie.memogame.domain.entities.GameResult

class FragmentWriteWinner : Fragment() {
    private lateinit var gameResult: GameResult
    private val fragmentWriteWinnerViewModel: FragmentWriteWinnerViewModel by lazy {
        ViewModelProvider(this)[FragmentWriteWinnerViewModel::class.java]
    }
    private var _binding: FragmentWriteWinnerBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        gameResult = requireArguments()
            .getParcelable<GameResult>(GAME_RESULT) as GameResult
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentWriteWinnerViewModel.done.observe(viewLifecycleOwner){
            findNavController()
                .navigate(R.id.action_fragmentWriteWinner_to_fragmentFinish)
        }

        fragmentWriteWinnerViewModel.setGameResult(gameResult)
        binding.buSaveWinner.setOnClickListener {
            val text = binding.tiName.text?.toString()?.trim() ?: throw RuntimeException("")
            fragmentWriteWinnerViewModel.sendRecordName(text)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteWinnerBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        private const val GAME_RESULT = "gameResult"
    }
}