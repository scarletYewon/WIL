package com.test.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val id = findViewById<EditText>(R.id.inputid)
        val password = findViewById<EditText>(R.id.inputpassword)

        //로그인 버튼 클릭시 메인화면으로 이동
        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
            val inputId = id.text.toString()
            val inputPassword = password.text.toString()


            if (inputId.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                if (!Pattern.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*\$", inputId)) {
                    Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
                } else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$", inputPassword)){
                    Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    //메인 화면이랑 연결해야 함
//                val intent = Intent(this, ::class.java)
                    startActivity(intent)
                }
            }
        }

        // signup 버튼 클릭 시 회원가입 화면으로 이동
        val btnSignup = findViewById<Button>(R.id.btn_signup)
        btnSignup.setOnClickListener {
            Toast.makeText(this, "회원가입 화면으로 이동합니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
}