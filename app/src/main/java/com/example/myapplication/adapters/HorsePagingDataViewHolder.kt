package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Horse
import kotlinx.android.synthetic.main.cardview_horse.view.*

class HorsePagingDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.name)
    private val age: TextView = view.findViewById(R.id.age)
    private val mother: TextView = view.findViewById(R.id.mother)
    private val father: TextView = view.findViewById(R.id.father)
    private val color: TextView = view.findViewById(R.id.color)
    private val location: TextView = view.findViewById(R.id.location)
    private val price: TextView = view.findViewById(R.id.price)
    private var imageButtonAddFavorite: ImageButton = view.findViewById(R.id.imageButtonAddFavorite)
    private val cardView: CardView = view.findViewById(R.id.cardView)

    fun bind(horse: Horse?) {
        name.text = horse?.document?.name ?: "-"
        age.text = horse?.document?.yearBirth ?: "-"
        mother.text = horse?.document?.mother ?: "-"
        father.text = "${horse?.document?.father ?: "-"} - "
        color.text = horse?.document?.color?.color ?: "-"
        location.text = horse?.document?.location?.city ?: "-"
        price.text = "${horse?.price ?:"р"}"
        //imageButtonAddFavorite.setImageResource(if (horse?.favorite == true) R.drawable.ic_favorite_added else R.drawable.ic_favorite)
    }

    fun initListeners(horse: Horse?) {
//        cardView.imageButtonAddFavorite.setOnClickListener {
//            if (horse.favorite) {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite)
//                horse.favorite = false
//            } else {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
//                horse.favorite = true
//            }
//        }

        //TODO поменять для избранного
        cardView.setOnClickListener {
            val bundle = bundleOf("ID" to horse?.idHorse)
            NavHostFragment
                .findNavController(cardView.findFragment())
                .navigate(R.id.action_navigation_search_to_detailInformationActivity, bundle)
        }

        cardView.scrollLayout.setOnClickListener {
            NavHostFragment
                .findNavController(cardView.findFragment())
                .navigate(R.id.action_navigation_search_to_viewingImagesActivity)
        }
    }

    fun setFavoriteOn(horse: Horse) {
        imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
//        horse.favorite = true
    }

    fun initListenersFavorite(horse: Horse) {
//        cardView.imageButtonAddFavorite.setOnClickListener {
//            if (horse.favorite) {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
//                horse.favorite = false
//            } else {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite)
//                horse.favorite = true
//            }
//        }

        cardView.setOnClickListener {
            val bundle = bundleOf("ID" to horse.idHorse)
            NavHostFragment
                .findNavController(cardView.findFragment())
                .navigate(R.id.action_navigation_favourite_to_detailInformationActivity, bundle)
        }

        cardView.scrollLayout.setOnClickListener {
            NavHostFragment.findNavController(cardView.findFragment()).navigate(R.id.action_navigation_favourite_to_viewingImagesActivity)
        }
    }

    companion object {

        private lateinit var context: Context

        fun create(parent: ViewGroup): HorsePagingDataViewHolder {
            context = parent.context
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_horse, parent, false)
            return HorsePagingDataViewHolder(view)
        }
    }
}