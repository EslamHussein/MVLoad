package com.mindvalley

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mindvalley.mvload.cache.inmemory.CacheManager
import com.mindvalley.mvload.core.BaseRequest
import com.mindvalley.mvload.core.JsonRequest
import com.mindvalley.mvload.core.listener.RequestListener
import com.mindvalley.mvload.load
import com.mindvalley.mvload.mapper.JsonMapper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView.load("https://www.premierleague.com/resources/ver/i/elements/premier-league-logo-header.png")

        val params = BaseRequest.RequestData.forRequest(url = "http://pastebin.com/raw/wgkJgazE")

        val cache = CacheManager<String>(50, 60000)
        val mapper = JsonMapper()

        val request = JsonRequest(mapper, cache)

        request.setListener(object : RequestListener<String> {

            override fun onSuccess(result: String) {

                textView.text = result
                Log.d("MainActivity", result)

            }

            override fun onError(result: Exception) {
                Log.d("MainActivity", result.localizedMessage)
            }

            override fun onComplete() {
            }


        })
        request.execute(params)

        request.execute(params)

    }
}
