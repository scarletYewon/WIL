package com.test.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.lang.Character.isDigit

class SignUp : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        val name = findViewById<EditText>(R.id.input_name)
        val id = findViewById<EditText>(R.id.input_id)
        val pw = findViewById<EditText>(R.id.input_password)
        val mbti = findViewById<EditText>(R.id.input_mbti)
        val loc = findViewById<EditText>(R.id.input_address)
        val like = findViewById<EditText>(R.id.input_interesting)
        val btn_signup = findViewById<Button>(R.id.btn_signup)
        btn_signup.setOnClickListener{
            Log.e("mbti",mbti.text.toString())
            createAccount(name.text.toString(),id.text.toString(),pw.text.toString(),mbti.text.toString(),loc.text.toString(),like.text.toString(),accept = true)
        }
    }
    fun createAccount(name: String, id: String, pw: String, mbti: String, loc: String, like:String, accept: Boolean) {
        // 숫자랑 영어로만 되어있는지 확인
        var eng = false
        var num = false
        for (i: Int in pw.indices) {
            if (isDigit(pw[i])) {
                num = true
            } else if (0x61.toChar() <= pw[i] && pw[i] <= 0x7A.toChar() || 0x41.toChar() <= pw[i] && pw[i] <= 0x5A.toChar()
            ) {
                eng = true
            }
            if (eng && num) {
                break
            }
        }
        // 빈칸 확인
        if (name.isNotEmpty() && id.isNotEmpty() && pw.isNotEmpty() && mbti.isNotEmpty() && loc.isNotEmpty() && like.isNotEmpty() && accept && num && eng) {
            auth?.createUserWithEmailAndPassword(id, pw)
                ?.addOnCompleteListener(this) { task ->
                    val uid = FirebaseAuth.getInstance().uid ?:""
                    if (task.isSuccessful) {
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference()
                        val dataInput = Users(
                            name, id, pw, mbti, loc, like,true
//                            binding.accept.isChecked
                        )
                        myRef.child("Users").child(uid).setValue(dataInput)
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()

                        moveLogInPage(task.result?.user)
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        else if (eng == false or num == false){
            Toast.makeText(
                this, "영어와 숫자를 포함하여 6자리 이상 입렵해주세요",
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (accept == false){
            Toast.makeText(
                this, "개인정보 수집 및 이용에 동의해주세요",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            Toast.makeText(
                this, "모든 칸을 채워주새요",
                Toast.LENGTH_SHORT
            ).show()
            Log.e("name",name)
            Log.e("id",id)
            Log.e("pw",pw)
            Log.e("mbti",mbti)
            Log.e("loc",loc)
            Log.e("like",like)
            Log.e("accept", accept.toString())

        }
    }
    // 로그인이 성공하면 다음 페이지로 넘어가는 함수
    fun moveLogInPage(user: FirebaseUser?) {
        // 파이어베이스 유저 상태가 있을 경우 다음 페이지로 넘어갈 수 있음
        if(user != null) {
            startActivity(Intent(this, LogIn::class.java))
        }
    }
}