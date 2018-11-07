package com.gram.dim

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_select_historical_site.*

class SelectHistoricalSiteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_historical_site)

        val selectHistoricalSiteItems = arrayListOf<SelectHistoricalSiteItem>(
                SelectHistoricalSiteItem("name","location","asdf")
        )


        val selectHistoricalSiteAdapter = SelectHistoricalSiteAdapter(this, selectHistoricalSiteItems)
        recycler_select_historical_site.adapter = selectHistoricalSiteAdapter

        val lm = LinearLayoutManager(this)
        recycler_select_historical_site.layoutManager = lm
        recycler_select_historical_site.setHasFixedSize(true)

    }
}
