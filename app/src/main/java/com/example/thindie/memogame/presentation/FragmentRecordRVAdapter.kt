package com.example.thindie.memogame.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thindie.memogame.databinding.ItemWinnerBinding
import com.example.thindie.memogame.domain.entities.GameRecord

class FragmentRecordRVAdapter : ListAdapter<GameRecord, GameRecordViewHolder>(RecordCallBack()) {

    private lateinit var binding: ItemWinnerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRecordViewHolder {
        binding = ItemWinnerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameRecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameRecordViewHolder, position: Int) {
        val winner = getItem(position)
        with(binding){
            tvName.text = winner.winnerName
            tvScore.text = winner.score.toString()
        }
    }

}

class GameRecordViewHolder(binding: ItemWinnerBinding) : ViewHolder(binding.root)

class RecordCallBack : DiffUtil.ItemCallback<GameRecord>() {
    override fun areItemsTheSame(oldItem: GameRecord, newItem: GameRecord): Boolean {
        return oldItem.score == newItem.score
    }

    override fun areContentsTheSame(oldItem: GameRecord, newItem: GameRecord): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}