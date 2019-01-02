package com.gram.dim

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_select_historical_site.*

class SelectHistoricalSiteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_historical_site)

        val selectHistoricalSiteItems = arrayListOf<SelectHistoricalSiteItem>(
                SelectHistoricalSiteItem("name", "location", "asdf")
        )


        val selectHistoricalSiteAdapter = SelectHistoricalSiteAdapter(this, selectHistoricalSiteItems)
        recycler_select_historical_site.adapter = selectHistoricalSiteAdapter

        val lm = LinearLayoutManager(this)
        recycler_select_historical_site.layoutManager = lm
        recycler_select_historical_site.setHasFixedSize(true)

        recycler_select_historical_site.addOnItemTouchListener(RecyclerItemClickListener(applicationContext, recycler_select_historical_site, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent: Intent = Intent(applicationContext,HistoricalSiteInformActivity::class.java)
                startActivity(intent)
            }

            override fun onLongItemClick(view: View?, position: Int) {
            }
        }))

    }
}
