package com.example.thindie.memogame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thindie.memogame.R
import com.example.thindie.memogame.databinding.FragmentRecordBinding

class FragmentRecord : Fragment() {

    private val fragmentRecordViewModel : FragmentRecordViewModel by lazy{
        ViewModelProvider(this)[FragmentRecordViewModel::class.java]
    }
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentRecordViewModel.showWinners()
        setupRecyclerView()
        setButtonListener()

    }

    private fun setButtonListener() {
        binding.buBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_fragmentRecord_to_fragmentStart
            )
        }
    }

    private fun setupRecyclerView() {
        val adapter = FragmentRecordRVAdapter()
        val recyclerView = binding.rvWinners
        recyclerView.adapter = adapter
        fragmentRecordViewModel.list.observe(viewLifecycleOwner){
                adapter.submitList(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}