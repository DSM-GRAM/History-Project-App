package com.gram.dim.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.gram.dim.R
import com.gram.dim.Model.SelectHistoricalSiteItem
import jp.wasabeef.glide.transformations.BlurTransformation




class SelectHistoricalSiteAdapter(val context: Context, val items: ArrayList<SelectHistoricalSiteItem>) :
        RecyclerView.Adapter<SelectHistoricalSiteAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_select_historical_site, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.text_select_historical_site_name)
        val locationText = itemView?.findViewById<TextView>(R.id.text_select_historical_site_location)
        val image = itemView?.findViewById<ImageView>(R.id.image_select_historical_site)

        fun bind(items: SelectHistoricalSiteItem, context: Context) {
            nameText?.text = items.historicalSiteName
            locationText?.text = items.historicalSiteLocation
            if (image != null) {
                Glide.with(itemView).load(items.historicalSiteImagePath)
                        .apply(bitmapTransform(BlurTransformation(25, 3)))
                        .into(image)
            }
        }
    }
}