package com.test.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class OnePost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onepost)

        val etComment = findViewById<EditText>(R.id.et_comment)
        val btnComment = findViewById<Button>(R.id.btn_comment)
        val tvCommentShow = findViewById<TextView>(R.id.tv_comment_show)
        val tvName = findViewById<TextView>(R.id.tv_name_onepost)
        val ivUser = findViewById<ImageView>(R.id.iv_user_onepost)

        val uid = FirebaseAuth.getInstance().uid ?:""
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val currentUserDB = myRef.child("Users").child(uid)

        currentUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tvName.text = snapshot.child("name").value.toString()//이름 값 가져오기
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        // 초기에는 댓글 입력 요소 및 댓글 표시를 보이지 않도록 설정
        tvName.visibility = View.GONE
        tvCommentShow.visibility = View.GONE
        ivUser.visibility = View.GONE

        var isLiked: Boolean = false
        val btnLike = findViewById<Button>(R.id.btn_like_onepost)
        btnLike.setOnClickListener {
            // 좋아요 버튼 클릭 시 이미지 변경
            if (isLiked) {
                btnLike.setBackgroundResource(R.drawable.fill_like)
            } else {
                btnLike.setBackgroundResource(R.drawable.like)
            }
            // isLiked 상태 변경
            isLiked = !isLiked
        }


        //제목 데이터
        val selectedItem = intent.getStringExtra("selectedItem")
        val tv_title_onepost = findViewById<TextView>(R.id.tv_title_onepost)
        tv_title_onepost.text = selectedItem
        Log.e("sssss",selectedItem.toString())


        //내용 데이터
        val context = intent.getStringExtra("context")
        val tv_memo_onepost = findViewById<TextView>(R.id.tv_memo_onepost)
        tv_memo_onepost.text = context


        btnComment.setOnClickListener {
            val commentText = etComment.text.toString().trim()
            if (commentText.isNotEmpty()) {
                // 댓글을 입력했을 때만 댓글 입력 요소 및 댓글 표시를 보이게 변경
                tvName.visibility = View.VISIBLE
                tvCommentShow.visibility = View.VISIBLE
                ivUser.visibility = View.VISIBLE
                // 비어있는 댓글 텍스트에 새로 입력한 댓글 표시
                val existingText = tvCommentShow.text.toString()
                val newComment = "$existingText\n$commentText"
                tvCommentShow.text = newComment
                // 여기서 extras에 저장하는 부분도 추가 가능하나, 잘 안되는듯.. 보수 필요
                intent.putExtra("comment", commentText)
                setResult(Activity.RESULT_OK, intent)
            }
            else {
                // 댓글이 비어있으면 토스트 메시지 띄우기
                Toast.makeText(this, "댓글을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


