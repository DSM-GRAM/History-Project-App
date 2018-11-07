package com.gram.dim

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SelectHistoricalSiteAdapter(val context: Context, val dogList: ArrayList<SelectHistoricalSiteItem>) :
        RecyclerView.Adapter<SelectHistoricalSiteAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_select_historical_site, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dogList[position], context)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.text_select_historical_site_name)
        val locationText = itemView?.findViewById<TextView>(R.id.text_select_historical_site_location)

        fun bind (items: SelectHistoricalSiteItem, context: Context) {

            nameText?.text = items.historicalSiteName
            locationText?.text = items.historicalSiteLocation
        }
    }
}