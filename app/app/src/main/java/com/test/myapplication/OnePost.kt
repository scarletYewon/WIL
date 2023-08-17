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


class OnePost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onepost)

        val etComment = findViewById<EditText>(R.id.et_comment)
        val btnComment = findViewById<Button>(R.id.btn_comment)
        val tvCommentShow = findViewById<TextView>(R.id.tv_comment_show)
        val tvName = findViewById<TextView>(R.id.tv_name_onepost)
        val ivUser = findViewById<ImageView>(R.id.iv_user_onepost)

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


        btnComment.setOnClickListener {
            val commentText = etComment.text.toString().trim()
            if (commentText.isNotEmpty()) {
                // 댓글을 입력했을 때만 댓글 입력 요소 및 댓글 표시를 보이게 변경
                tvName.visibility = View.VISIBLE
                tvCommentShow.visibility = View.VISIBLE
                ivUser.visibility = View.VISIBLE

                // 기존 댓글 텍스트에 새 댓글 추가하여 표시
                val existingText = tvCommentShow.text.toString()
                val newComment = "$existingText\n$commentText"
                tvCommentShow.text = newComment

                // 여기서 extras에 저장하는 부분도 추가 가능하나, 필요시에 활용하실 수 있습니다.
                 val intent = Intent()
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


