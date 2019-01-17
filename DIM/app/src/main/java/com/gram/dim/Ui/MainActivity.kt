package com.gram.dim.Ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gram.dim.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vladivostok.setOnClickListener {
            var intent = Intent(this, SelectHistoricalSiteActivity::class.java)
            intent.putExtra("choose", "vladivostok")
            startActivity(intent)
        }

        ussuriysk.setOnClickListener {
            var intent = Intent(this, SelectHistoricalSiteActivity::class.java)
            intent.putExtra("choose", "usulisk")
            startActivity(intent)
        }
    }
}
