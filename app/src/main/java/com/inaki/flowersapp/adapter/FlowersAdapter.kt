package com.inaki.flowersapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inaki.flowersapp.R
import com.inaki.flowersapp.model.Flowers
import com.squareup.picasso.Picasso

/**
 * This class is to load and create the adapter for the recycler view
 */
class FlowersAdapter(
    private val flowers: Flowers = Flowers()
) : RecyclerView.Adapter<FlowerViewHolder>() {

    fun updateFlowers(flowersUpdated: Flowers) {
        flowers.addAll(flowersUpdated)
        notifyDataSetChanged()
    }


    /**
     * This method will be in charge of inflating the flower_item.xml
     * and creating the ViewHolder in this case FlowerViewHolder
     *
     * Then we pass the layout to the view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        // this variable is going to hold reference to the view and inflate the flower_item.xml
        val flowerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.flower_item, parent, false)

        // getting the instance of Flower view holder and passing the item view
        // item view will be you layout flower_item.xml
        return FlowerViewHolder(flowerView)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        // create a variable to have each flower form the data set
        val flower = flowers[position]

        // here I am setting the data coming from server into our data set into the views
        // view holder contains those views, where I can set each one
        holder.flowerCategory.text = flower.category
        holder.flowerName.text = flower.name
        holder.flowerPrice.text = flower.price.toString()

        // Picasso is used to load and download images form server into you views
        Picasso
            .get()
            .load(IMAGE_URL + flower.photo)
            .into(holder.flowerImage)
    }

    override fun getItemCount(): Int = flowers.size

    companion object {
        // this is the URL that we are going to use in order to download the image with picasso
        private const val IMAGE_URL = "http://services.hanselandpetal.com/photos/"
    }
}

/**
 * This is our View Holder to be passed to the adapter
 * it is going to hols the views in the flower_item layout
 * We need to pass a View object to the constructor of the View Holder
 */
class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // right here we declare and assign the views
    val flowerName: TextView = itemView.findViewById(R.id.flower_name)
    val flowerCategory: TextView = itemView.findViewById(R.id.flower_category)
    val flowerPrice: TextView = itemView.findViewById(R.id.flower_price)
    val flowerImage: ImageView = itemView.findViewById(R.id.flower_image)
}