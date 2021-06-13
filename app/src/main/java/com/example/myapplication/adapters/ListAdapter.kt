package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.interfaces.OnItemClickListener

class ListAdapter(private val list: List<String>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_text,
            parent,
            false
        )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.city?.text = list[position]

        holder.cardView.setOnClickListener {
            onItemClickListener.onItemClicked(
                position,
                list[position]
            )
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city: TextView? = itemView.findViewById(R.id.textCityName)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}