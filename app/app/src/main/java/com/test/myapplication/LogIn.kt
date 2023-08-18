package com.test.myapplication

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class LogIn : AppCompatActivity() {
    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()
        val id = findViewById<EditText>(R.id.input_id)
        val password = findViewById<EditText>(R.id.input_password)

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
                }
                if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$", inputPassword)){
                    Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                } else{
                    signinEmail(inputId,inputPassword)
                }
            }
        }

        // signup 버튼 클릭 시 회원가입 화면으로 이동
        val btnSignup = findViewById<Button>(R.id.btn_signup)
        btnSignup.setOnClickListener {
            Toast.makeText(this, "회원가입 화면으로 이동합니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            //애니메이션 추가
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
        }

    }
    // 로그인
    fun signinEmail(id:String,pw:String) {
        auth?.signInWithEmailAndPassword(id,pw)
            ?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    // Login, 아이디와 패스워드가 맞았을 때
                    Toast.makeText(this, "로그인성공", Toast.LENGTH_LONG).show()
                    moveMainPage(task.result?.user)
                } else {
                    // Show the error message, 아이디와 패스워드가 틀렸을 때
                    Toast.makeText(this, "로그인실패", Toast.LENGTH_LONG).show()
                }
            }
        val am: AccountManager = AccountManager.get(this) // "this" refeences the current Context
        val accounts: Array<out Account> = am.getAccountsByType("com.google")
    }
    // 로그인이 성공하면 다음 페이지로 넘어가는 함수
    private fun moveMainPage(user: FirebaseUser?) {
        // 파이어베이스 유저 상태가 있을 경우 다음 페이지로 넘어갈 수 있음
        if(user != null) {
            val intent = (Intent(this, Main::class.java))
            startActivity(intent)
        }
    }
}