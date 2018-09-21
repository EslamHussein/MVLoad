package com.mindvalley

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mindvalley.mvload.load
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        imageView.load("https://www.premierleague.com/resources/ver/i/elements/premier-league-logo-header.png")

    }
}
