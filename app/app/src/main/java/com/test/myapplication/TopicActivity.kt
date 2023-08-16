package com.test.myapplication

import android.annotation.SuppressLint
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class TopicActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val mypage = findViewById<ImageView>(R.id.maypage_btn)
        val new = findViewById<ImageView>(R.id.New_btn)
        val like = findViewById<ImageView>(R.id.like_img)
        mypage.setOnClickListener{

        }
        new.setOnClickListener {

        }
        like.setOnClickListener {

            Toast.makeText(this, "좋아요 ", Toast.LENGTH_SHORT).show()
        }

    }
}
