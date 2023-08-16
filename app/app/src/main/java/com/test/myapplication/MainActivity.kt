package com.test.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val health = findViewById<LinearLayout>(R.id.health)
        val music = findViewById<LinearLayout>(R.id.music)
        val fashion = findViewById<LinearLayout>(R.id.fashion)
        val maypage_btn = findViewById<ImageView>(R.id.maypage_btn)




    }
}