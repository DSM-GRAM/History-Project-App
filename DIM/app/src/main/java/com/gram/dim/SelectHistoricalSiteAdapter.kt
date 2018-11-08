package com.gram.dim

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropCircleTransformation


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
        val image = itemView?.findViewById<CardView>(R.id.card_select_historical_site)

        fun bind(items: SelectHistoricalSiteItem, context: Context) {
            nameText?.text = items.historicalSiteName
            locationText?.text = items.historicalSiteLocation
            Glide.with(itemView).load(items.historicalSiteImagePath)
                    bitmapTransform(BlurTransformation(context, 25, 2))
                    .into(iv_selphone)
        }
    }
}