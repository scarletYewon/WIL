
package com.test.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Topic3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val uid = FirebaseAuth.getInstance().uid ?:""
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val currentUserDB = myRef.child("Users").child(uid)
        val text_name = findViewById<TextView>(R.id.text_name)
        currentUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                text_name.text = snapshot.child("name").value.toString()//이름 값 가져오가ㅁ
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        val edit_text1 = findViewById<TextView>(R.id.edit_text1) // 게시판 값 가져오기
        edit_text1.text = "패션게시판"


        //마이페이지
        val mypage_btn = findViewById<ImageView>(R.id.maypage_btn)
        mypage_btn.setOnClickListener {
            Toast.makeText(this, "마이페이지로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MyPage::class.java)
            startActivity(intent)
        }
        //NEW
        val New_btn = findViewById<ImageView>(R.id.New_btn)
        New_btn.setOnClickListener {
            Toast.makeText(this, "새로 만들기로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,NewPost::class.java)
            startActivity(intent)
        }
        //좋아요 버튼
        var isLiked: Boolean = false
        val like_img = findViewById<ImageView>(R.id.like_img)
        val unlike_img = findViewById<ImageView>(R.id.unlike_img)
        like_img.setOnClickListener {
            Toast.makeText(this, "좋아요 ", Toast.LENGTH_SHORT).show()


            if (isLiked) {
                like_img.setBackgroundResource(R.drawable.like_pressed);

            } else  {
                like_img.setBackgroundResource(R.drawable.like_unpressed);
            }
            isLiked = !isLiked
        }

        //리스트 뷰 항목
        val TopicList = findViewById<ListView>(R.id.TopicList)
        val UserList = arrayListOf<Topic_Item>()
        val Topic_Adpater = Topic_Adpater(this,UserList)
        TopicList.adapter = Topic_Adpater

        UserList.add(Topic_Item("인기 빈티지 패션",""))
        UserList.add(Topic_Item("계절별 best5 의상",""))
        if (intent.getStringExtra("title")==null){
        } else{
            UserList.add(Topic_Item(intent.getStringExtra("title"),""))
        }

        TopicList.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
            val selectedItem = UserList[position]
            val intent = Intent(this,OnePost::class.java)
            intent.putExtra("selectedItem", selectedItem.title)
            intent.putExtra("context", selectedItem.context.toString())
            startActivity(intent)
        }
    }
}
