package com.test.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.Dispatchers.Main
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val name = findViewById<EditText>(R.id.input_name)
        val id = findViewById<EditText>(R.id.inputid)
        val password = findViewById<EditText>(R.id.input_password)
        val mbti = findViewById<EditText>(R.id.input_mbti)
        val address = findViewById<EditText>(R.id.input_address)
        val interest = findViewById<EditText>(R.id.input_interest)

        val btnSignup = findViewById<Button>(R.id.btn_signup)
        // SIGNUP버튼 클릭 시 회원가입 화면으로 이동
        btnSignup.setOnClickListener {
            val inputName = name.text.toString()
            val inputId = id.text.toString()
            val inputPassword = password.text.toString()
            val inputMbti = mbti.text.toString()
            val inputAddress = address.text.toString()
            val inputInterest = interest.text.toString()
        // 이름, 아이디, 비밀번호에 대해서만 유효성 검사
        if (inputName.isEmpty() || inputId.isEmpty() || inputPassword.isEmpty() || inputMbti.isEmpty() || inputAddress.isEmpty() || inputInterest.isEmpty()){
            Toast.makeText(this, "위의 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else{
            if(!Pattern.matches("^[가-힣]*\$", inputName)){
                Toast.makeText(this, "이름을 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if(!Pattern.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*\$", inputId)){
                Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$", inputPassword)){
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else{
                //이용약관 다이얼로그 띄우기
                val builder = AlertDialog.Builder(this)
                builder.setTitle("이용약관")
                    .setMessage("1. 개인정보의 수집‧이용 목적\n" +
                            "WIL에 대한 이용문의 민원에 대한 처리\n" +
                            "\n" +
                            "2. 수집하는 개인정보의 항목\n" +
                            "작성자 이름, 휴대폰 번호\n" +
                            "\n" +
                            "3. 개인정보 수집에 대한 거부 권리 및 그에 따른 제한사항\n" +
                            "이용자는 WIL의 개인정보 수집에 대한 동의 요청을 거부할 권리가 있으며 거부할 경우 WIL 이용문의 서비스 이용에 대한 제한을 받을 수 있습니다.")
                    .setPositiveButton("동의",
                        DialogInterface.OnClickListener { dialog, id ->
                            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LogIn::class.java)
                            startActivity(intent)
                        })
                    .setNegativeButton("취소",
                        DialogInterface.OnClickListener { dialog, id ->
                            Toast.makeText(this, "이용약관에 동의하지 않으면 회원가입이 불가합니다.", Toast.LENGTH_SHORT).show()
                        })
                builder.show()
               }
            }
        }
    }
}