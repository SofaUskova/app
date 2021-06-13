package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.interfaces.OnItemClickListener
import com.example.myapplication.models.Horse
import kotlinx.android.synthetic.main.cardview_horse_little.view.*

class GridAdapter(private val onItemClickListener: OnItemClickListener, val horses: List<Horse>) :
    RecyclerView.Adapter<GridAdapter.MyViewHolder>() {

    override fun getItemCount() = horses.size

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
                position + 1,
                ""
            )
        }

        holder.cardView.horseName.text = horses[position].name
        holder.cardView.horsePrice.text = "horses[position].price"
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}