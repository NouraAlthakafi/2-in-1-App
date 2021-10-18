package com.example.a2in1app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_game.view.*

class rvAllGuesses(private var guesses: ArrayList<String>): RecyclerView.Adapter<rvAllGuesses.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_game, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val aGuess = guesses[position]
        holder.itemView.apply{
            tvGame.text = aGuess
        }
    }

    override fun getItemCount()= guesses.size
}