package com.gram.dim

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enterOX.setOnClickListener {
            var intent = Intent(this, OorXTestActivity::class.java)
            startActivity(intent)
        }
        enterDrag.setOnClickListener {
            var intent = Intent(this, DragTestActivity::class.java)
            startActivity(intent)
        }
    }
}
