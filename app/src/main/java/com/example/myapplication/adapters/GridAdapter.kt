package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.interfeises.OnItemClickListener

class GridAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<GridAdapter.MyViewHolder>() {

    override fun getItemCount() = 15

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cardview_horse_little,
            parent,
            false
        )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.cardView.setOnClickListener {
            onItemClickListener.onItemClicked(
                position,
                ""
            )
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}