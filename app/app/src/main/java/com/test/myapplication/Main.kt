package com.test.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val health = findViewById<LinearLayout>(R.id.health)
        val music = findViewById<LinearLayout>(R.id.music)
        val fashion = findViewById<LinearLayout>(R.id.fashion)
        val mypage_btn = findViewById<ImageView>(R.id.mypage_btn)
        mypage_btn.setOnClickListener {
            val intent = Intent(this,MyPage::class.java)
            startActivity(intent)
            Toast.makeText(this, "마이페이지로 이동합니다 ", Toast.LENGTH_SHORT).show()
        }
//      //운동게시판
        health.setOnClickListener {
            Toast.makeText(this, "운동게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Topic1::class.java)
            startActivity(intent)

        }
        //음악게시판
        music.setOnClickListener {
            Toast.makeText(this, "음악게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,Topic2::class.java)
            startActivity(intent)
        }
        //패션게시판
        fashion.setOnClickListener {
            Toast.makeText(this, "패션게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,Topic3::class.java)
            startActivity(intent)
        }


    }
}