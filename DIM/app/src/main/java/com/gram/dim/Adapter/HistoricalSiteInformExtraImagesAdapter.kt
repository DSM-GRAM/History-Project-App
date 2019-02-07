package com.gram.dim.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gram.dim.Model.HistoricalSiteInformExtraImagesItem
import com.gram.dim.R

class HistoricalSiteInformExtraImagesAdapter(val context: Context, val items: ArrayList<HistoricalSiteInformExtraImagesItem>):RecyclerView.Adapter<HistoricalSiteInformExtraImagesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_historical_site_inform_extra_images, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val nameText = itemView?.findViewById<TextView>(R.id.text_historical_site_inform_extra_images_name)
        private val locationText = itemView?.findViewById<TextView>(R.id.text_historical_site_inform_extra_images_location)
        private val image = itemView?.findViewById<ImageView>(R.id.image_historical_site_inform_extra_images)

        fun bind (items: HistoricalSiteInformExtraImagesItem) {
            nameText?.text = items.name
            locationText?.text = items.location
            if (image != null) {
                Glide.with(itemView).load(items.imagePath).apply(RequestOptions().override(160, 175)).into(image)
            }
        }


    }
}