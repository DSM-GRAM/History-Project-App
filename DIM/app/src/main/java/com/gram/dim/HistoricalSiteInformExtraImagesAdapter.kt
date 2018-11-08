package com.gram.dim

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HistoricalSiteInformExtraImagesAdapter(val context: Context, val items: ArrayList<HistoricalSiteInformExtraImagesItem>):RecyclerView.Adapter<HistoricalSiteInformExtraImagesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_historical_site_inform_extra_images, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.text_historical_site_inform_extra_images_name)
        val locationText = itemView?.findViewById<TextView>(R.id.text_historical_site_inform_extra_images_location)

        fun bind (items: HistoricalSiteInformExtraImagesItem, context: Context) {
            nameText?.text = items.name
            locationText?.text = items.location
        }


    }
}