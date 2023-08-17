package com.test.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class TopicActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topicactivity)

        val mypage_btn = findViewById<ImageView>(R.id.maypage_btn)
        val New_btn = findViewById<ImageView>(R.id.New_btn)
        val like_img = findViewById<ImageView>(R.id.like_img)
        val protein = findViewById<RelativeLayout>(R.id.protein)
        val running = findViewById<RelativeLayout>(R.id.running)

        val nameText = findViewById<TextView>(R.id.text_name)  //이름 값 가져오기
        val strData = intent.getStringExtra("mypageActivity")
        nameText.text = "$strData"

        val edit_text1 = findViewById<TextView>(R.id.edit_text1) // 게시판 값 가져오기
        val edit_Data1 = intent.getStringExtra("healthActivity")
        edit_text1.text = "$edit_Data1"

        // 조회수
        val random1 = Random()
        val num1 = random1.nextInt(100)
        val text_1 = findViewById<TextView>(R.id.text_1)
        text_1.text = "조회수 $num1"

        val random1_1 = Random()
        val num1_1 = random1_1.nextInt(100)
        val text_1_1 = findViewById<TextView>(R.id.text_2)
        text_1_1.text = "조회수 $num1_1"
        // 댓글
        val random2 = Random()
        val num2 = random2.nextInt(100)
        val text_2 = findViewById<TextView>(R.id.text_1_1)
        text_2.text = "댓글 $num2"

        val random2_1 = Random()
        val num2_1 = random2_1.nextInt(100)
        val text_2_1 = findViewById<TextView>(R.id.text_2_1)
        text_2_1.text = "댓글 $num2_1"
        // 좋아요
        val random3 = Random()
        val num3 = random3.nextInt(100)
        val text_3 = findViewById<TextView>(R.id.text_1_2)
        text_3.text = "좋아요 $num3"

        val random3_1 = Random()
        val num3_1 = random3_1.nextInt(100)
        val text_3_1 = findViewById<TextView>(R.id.text_2_2)
        text_3_1.text = "좋아요 $num3_1"

        //마이페이지
        mypage_btn.setOnClickListener{
            Toast.makeText(this, "마이페이지로 이동합니다 ", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this,mypageActivity::class.java)
//            startActivity(intent)
        }
        //NEW
        New_btn.setOnClickListener {
            Toast.makeText(this, "새로 만들기로 이동합니다 ", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this,newpostActivity::class.java)
//            startActivity(intent)
        }
        //좋아요 버튼
        like_img.setOnClickListener {
            Toast.makeText(this, "좋아요 ", Toast.LENGTH_SHORT).show()

        }



        protein.setOnClickListener{

//            val intent = Intent(this,defaultActivity::class.java)
//            startActivity(intent)
        }

        running.setOnClickListener{

//            val intent = Intent(this,mypageActivity::class.java)
//            startActivity(intent)
        }

    }
}
