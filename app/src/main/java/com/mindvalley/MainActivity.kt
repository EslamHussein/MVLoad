package com.mindvalley

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mindvalley.mvload.load
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.load("https://www.premierleague.com/resources/ver/i/elements/premier-league-logo-header.png",
                placeHolder = R.mipmap.ic_launcher)
        button.setOnClickListener {

            startActivity(Intent(this, DetailsActivity::class.java))
        }
    }
}
