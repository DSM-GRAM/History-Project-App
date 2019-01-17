package com.gram.dim.Ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.gram.dim.Adapter.SelectHistoricalSiteAdapter
import com.gram.dim.Connector.ApiClient
import com.gram.dim.Model.SelectHistoricalSiteItem
import com.gram.dim.Model.SelectHistoricalSiteModel
import com.gram.dim.R
import com.gram.dim.Util.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_select_historical_site.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectHistoricalSiteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_historical_site)

        var intent = intent
        val locationName: String = intent.getStringExtra("choose")
        var selectHistoricalSiteItems: ArrayList<SelectHistoricalSiteItem> = ArrayList()

        text_select_historical_site_toolbar_name.text = when (locationName) {
            "vladivostok" -> "블라디보스톡"
            "usulisk" -> "우수리스크"
            else -> "error"
        }

        btn_select_historical_site_toolbar_back.setOnClickListener { finish() }

        val selectHistoricalSiteAdapter = SelectHistoricalSiteAdapter(this, selectHistoricalSiteItems)
        recycler_select_historical_site.adapter = selectHistoricalSiteAdapter

        val lm = LinearLayoutManager(this)
        recycler_select_historical_site.layoutManager = lm
        recycler_select_historical_site.setHasFixedSize(true)

        recycler_select_historical_site.addOnItemTouchListener(RecyclerItemClickListener(applicationContext, recycler_select_historical_site, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                intent = Intent(applicationContext, HistoricalSiteInformActivity::class.java)
                startActivity(intent)
            }

            override fun onLongItemClick(view: View?, position: Int) {
            }
        }))

        //서버 연동
        ApiClient.api.getSites(when (locationName) {"vladivostok" -> "bla" "usulisk" -> "usu" else -> "error"}).enqueue(object : Callback<ArrayList<SelectHistoricalSiteModel>> {

            override fun onResponse(call: Call<ArrayList<SelectHistoricalSiteModel>>, response: Response<ArrayList<SelectHistoricalSiteModel>>) {
                if (response.code() == 200) {
                    for (i in response.body()!!.indices) {
                        selectHistoricalSiteItems.add(SelectHistoricalSiteItem(response.body()!![i].name, response.body()!![i].location, response.body()!![i].imagePath))
                    }
                    selectHistoricalSiteAdapter.notifyDataSetChanged()
                } else {
                    Log.d("Debug","ㅠ")
                    Toast.makeText(applicationContext, "오류", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<SelectHistoricalSiteModel>>, t: Throwable) {
                Log.d("Debug","ㅜㅜㅜㅜㅜ")
                Toast.makeText(applicationContext, "오류", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
