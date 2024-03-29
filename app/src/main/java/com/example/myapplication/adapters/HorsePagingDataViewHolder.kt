package com.example.myapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Horse
import com.example.myapplication.models.SalesContract

class HorsePagingDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.name)
    private val age: TextView = view.findViewById(R.id.age)
    private val mother: TextView = view.findViewById(R.id.mother)
    private val father: TextView = view.findViewById(R.id.father)
    private val color: TextView = view.findViewById(R.id.color)
    private val location: TextView = view.findViewById(R.id.location)
    private val price: TextView = view.findViewById(R.id.price)
    private val imageView: ImageView = view.findViewById(R.id.imageView)
    private val cardView: CardView = view.findViewById(R.id.cardView)

    @SuppressLint("SetTextI18n")
    fun bind(horse: Horse?, contract: SalesContract) {
        name.text = horse?.name ?: "-"
        age.text = horse?.yearBirth ?: "-"
        mother.text = horse?.mother ?: "-"
        father.text = "${horse?.father ?: "-"} - "
        color.text = horse?.color?.color ?: "-"
        location.text = horse?.location?.city ?: "-"
        price.text = "${contract.price}р"
    }

    fun initListeners(horse: Horse?) {
        cardView.setOnClickListener {
            val bundle = bundleOf("ID" to horse?.idHorse)
            NavHostFragment
                .findNavController(cardView.findFragment())
                .navigate(R.id.action_navigation_search_to_detailInformationActivity, bundle)
        }
    }

//    fun setFavoriteOn(horse: Horse) {
//        imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
////        horse.favorite = true
//    }

    fun initListenersFavorite(horse: Horse) {
        cardView.setOnClickListener {
            val bundle = bundleOf("ID" to horse.idHorse)
            NavHostFragment
                .findNavController(cardView.findFragment())
                .navigate(R.id.action_navigation_favourite_to_detailInformationActivity, bundle)
        }
    }

    companion object {
        fun create(parent: ViewGroup): HorsePagingDataViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_horse, parent, false)
            return HorsePagingDataViewHolder(view)
        }
    }
}