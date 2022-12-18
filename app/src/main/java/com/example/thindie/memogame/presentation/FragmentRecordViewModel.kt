package com.example.thindie.memogame.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.memogame.data.repository.MemoRepositoryImpl
import com.example.thindie.memogame.domain.entities.GameRecord
import com.example.thindie.memogame.domain.useCases.GetWinnersUseCase

class FragmentRecordViewModel(application: Application)
    : AndroidViewModel(application) {
    private val repository = MemoRepositoryImpl(application)
    private val winners = GetWinnersUseCase(repository).getWinners().list


    private val _list = MutableLiveData<List<GameRecord>>()
    val list : LiveData<List<GameRecord>>
    get() = _list

    fun showWinners(){
        _list.value = winners
    }

}