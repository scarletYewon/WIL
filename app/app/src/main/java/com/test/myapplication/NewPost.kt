package com.test.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.widget.ListView


class NewPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newpost)

        val title = findViewById<EditText>(R.id.et_title)
        val memo = findViewById<EditText>(R.id.et_memo)
        val post = findViewById<Button>(R.id.btn_post)

        val mypage_btn = findViewById<Button>(R.id.goMyPage)
        mypage_btn.setOnClickListener {
            Toast.makeText(this, "마이페이지로 이동합니다 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MyPage::class.java)
            startActivity(intent)
        }

        // 제목과 내용 둘 중 하나라도 입력 안되면 토스트 메시지 띄우기
        post.setOnClickListener {

            val inputTitle = title.text.toString().trim()
            val inputMemo = memo.text.toString().trim()

            if (title.text.toString().trim().isEmpty() || memo.text.toString().trim().isEmpty())
             {
                Toast.makeText(this, "제목과 내용 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 버튼 클릭 시 Test 화면으로 이동 (Topic으로 비꾸세요.)
            else {
                //Topic1 class 로 데이터 전달 위한 변수명 설정
                val title_test = findViewById<EditText>(R.id.et_title)
                val strData_title = title_test.text.toString()
                val strData_memo = memo.text.toString()


                // 리스트 항목 추가.

                val intent = Intent(this, Topic1::class.java)
                // test class 로 key 값 전달
                intent.putExtra("title", strData_title)
                intent.putExtra("memo", strData_memo)
                startActivity(intent)


            }
        }
    }
}