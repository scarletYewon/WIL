package com.test.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val health = findViewById<LinearLayout>(R.id.health)
        val music = findViewById<LinearLayout>(R.id.music)
        val fashion = findViewById<LinearLayout>(R.id.fashion)
        val maypage_btn = findViewById<ImageView>(R.id.maypage_btn)
        maypage_btn.setOnClickListener {
            val intent = Intent(this,MyPage::class.java)
            startActivity(intent)
            Toast.makeText(this, "마이페이지로 이동합니다 ", Toast.LENGTH_SHORT).show()
        }
//      //운동게시판
        health.setOnClickListener {
            Toast.makeText(this, "운동게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val health_name = findViewById<TextView>(R.id.health_name)
            val str_id = health_name.text.toString()
            val intent = Intent(this, Topic::class.java)
            intent.putExtra("healthActivity", str_id)
            startActivity(intent)

        }
        //음악게시판
        music.setOnClickListener {
            Toast.makeText(this, "음악게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val music_name = findViewById<TextView>(R.id.music_name)
            val str_id = music_name.text.toString()
            intent.putExtra("mainActivity", str_id)

//            val intent = Intent(this,TopicActivity::class.java)
//            startActivity(intent)
        }
        //패션게시판
        fashion.setOnClickListener {
            Toast.makeText(this, "패션게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val fashion_name = findViewById<TextView>(R.id.fashion_name)
            val str_id = fashion_name.text.toString()
            intent.putExtra("mainActivity", str_id)

//            val intent = Intent(this,TopicActivity::class.java)
//            startActivity(intent)
        }


    }
}