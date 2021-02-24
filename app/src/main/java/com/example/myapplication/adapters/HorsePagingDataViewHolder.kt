package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Horse
import kotlinx.android.synthetic.main.card_view_horse.view.*

class HorsePagingDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.name)
    private val age: TextView = view.findViewById(R.id.age)
    private val mother: TextView = view.findViewById(R.id.mother)
    private val father: TextView = view.findViewById(R.id.father)
    private val color: TextView = view.findViewById(R.id.color)
    private val location: TextView = view.findViewById(R.id.location)
    private val price: TextView = view.findViewById(R.id.price)
    private val cardView: CardView = view.findViewById(R.id.cardView)

    private lateinit var parentHorse: ViewGroup

    private var horse: Horse? = null

    fun bind(horse: Horse?) {
        name.text = horse?.name
        age.text = horse?.age
        mother.text = horse?.mother
        father.text = horse?.father
        color.text = horse?.color
        location.text = horse?.location
        price.text = horse?.price
    }

    fun initListeners(horse: Horse) {
        cardView.imageButtonAddFavorite.setOnClickListener {
            if (horse.favorite) {
                cardView.imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
                horse.favorite = false
            } else {
                cardView.imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite)
                horse.favorite = true
            }
        }

        //TODO поменять для избранного
        cardView.setOnClickListener {
            NavHostFragment.findNavController(cardView.findFragment()).navigate(R.id.action_navigation_search_to_detailInformationActivity)
        }

        cardView.scrollLayout.setOnClickListener {
            NavHostFragment.findNavController(cardView.findFragment()).navigate(R.id.action_navigation_search_to_viewingImagesActivity)
        }
    }

    companion object {

        private lateinit var context: Context

        fun create(parent: ViewGroup): HorsePagingDataViewHolder {
            context = parent.context
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_horse, parent, false)
            return HorsePagingDataViewHolder(view)
        }
    }
}