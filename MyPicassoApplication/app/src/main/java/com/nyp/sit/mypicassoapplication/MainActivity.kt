package com.nyp.sit.mypicassoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var str = "https://image.tmdb.org/t/p/w185/8bZ7guF94ZyCzi7MLHzXz6E5Lv8.jpg"
        Picasso.get().load(str).into(iv)
    }
}