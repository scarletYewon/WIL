package com.test.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val title = intent.getStringExtra("title")
        val memo = intent.getStringExtra("memo")

        val tvTitle = findViewById<TextView>(R.id.tv_title_test)
        val tvMemo = findViewById<TextView>(R.id.tv_memo_test)

        tvTitle.text = title
        tvMemo.text = memo
    }
}