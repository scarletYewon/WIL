package com.android.whatilove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newpost)

        val title = findViewById<EditText>(R.id.et_title)
        val memo = findViewById<EditText>(R.id.et_memo)
        val post = findViewById<Button>(R.id.btn_post)

        // 제목과 내용 둘 중 하나라도 입력 안되면 토스트 메시지 띄우기
        post.setOnClickListener {
            if (title.text.toString().trim().isEmpty() || memo.text.toString().trim()
                    .isEmpty()
            ) {
                Toast.makeText(this, "제목과 내용 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 버튼 클릭 시 topic 액티비티로 이동
            val intent = Intent(this, topic::class.java)
            intent.putExtra("title", title.text.toString())
            startActivity(intent)
        }
    }
}