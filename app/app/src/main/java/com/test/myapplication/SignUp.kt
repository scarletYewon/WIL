package com.test.myapplication


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.regex.Pattern
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.lang.Character.isDigit

class SignUp : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        
        val name = findViewById<EditText>(R.id.input_name)
        val id = findViewById<EditText>(R.id.input_id)
        val password = findViewById<EditText>(R.id.input_password)
        val mbti = findViewById<EditText>(R.id.input_mbti)
        val address = findViewById<EditText>(R.id.input_address)
        val interest = findViewById<EditText>(R.id.input_interest)

        val btnSignup = findViewById<Button>(R.id.btn_signup)
        auth = FirebaseAuth.getInstance()

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
            }
//            else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{5,10}\$", inputId)){
//                Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
//            }
            else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}\$", inputPassword)){
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
            createAccount(name.text.toString(),id.text.toString(),password.text.toString(),mbti.text.toString(),address.text.toString(),interest.text.toString(),accept = true)
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