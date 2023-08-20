package com.test.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class Modify : AppCompatActivity() {
    lateinit var mypageButton: ImageButton
    lateinit var profileButton: ImageButton
//    lateinit var residence: EditText
//    lateinit var mbti: EditText
//    lateinit var pass: EditText
//    lateinit var interesting: EditText
    lateinit var modificationButton: ImageButton
//    lateinit var slogun: TextView
//    lateinit var name: TextView
//    lateinit var nameIndex: TextView
    lateinit var globalName : String
    lateinit var globalEmail : String
    lateinit var globalPass : String
    val uid = FirebaseAuth.getInstance().uid ?:""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify )

        globalName = intent.getStringExtra("name").toString()
        globalEmail = intent.getStringExtra("email").toString()
        globalPass = intent.getStringExtra("pw").toString()

//
//        slogun=findViewById(R.id.slogun)
        val name=findViewById<TextView>(R.id.name)
        name.text = globalName

        val email = findViewById<TextView>(R.id.id)
        email.text = globalEmail

        val pwd = findViewById<TextView>(R.id.pass)
        pwd.text = globalPass


        val loc = findViewById<EditText>(R.id.residence)
        val like = findViewById<EditText>(R.id.interesting)
        val mbti = findViewById<EditText>(R.id.mbti)

//
//        val slogunText:String=slogun.text.toString()
//        val nameText:String=name.text.toString()
//        val builder= SpannableStringBuilder(slogunText)
//        val builder2= SpannableStringBuilder(nameText)
//        nameIndex=findViewById(R.id.name)
//
//        val nameIndexText=nameIndex.text.toString()
//        val charFind=','
//
//        val index=nameIndexText.indexOf(charFind)
//
//
//        val Wcolor= ForegroundColorSpan(Color.RED)
//        builder.setSpan(Wcolor, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        val ILcolor= ForegroundColorSpan(Color.RED)
//        builder.setSpan(ILcolor, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        val nameStyle= StyleSpan(Typeface.BOLD)
//        builder2.setSpan(nameStyle,0,index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        val nameSize= RelativeSizeSpan(1.6f)
//        builder2.setSpan(nameSize,0,index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//
//        slogun.text = builder
//        name.text=builder2
//        residence=findViewById(R.id.residence)
//        mbti=findViewById(R.id.mbti)
//        pass=findViewById(R.id.pass)
//        interesting=findViewById(R.id.interesting)


        val logo = findViewById<ImageView>(R.id.logo)
        logo.setOnClickListener{
            val intent = Intent(this,Main::class.java)
            startActivity(intent)
        }



        mypageButton=findViewById(R.id.profileButton)
        mypageButton.setOnClickListener{
            val intent= Intent(this,MyPage::class.java)
            startActivity(intent)
        }

        modificationButton = findViewById(R.id.modificationButton)
        modificationButton.setOnClickListener{
            val editLoc = loc.text.toString()
            val editLike = like.text.toString()
            val editMbti = mbti.text.toString()
            if (editLoc.isEmpty() || editLike.isEmpty()|| editMbti.isEmpty()) {
                Toast.makeText(this, "빈칸을 다 채워주세요", Toast.LENGTH_SHORT).show()
            } else {
                onDeleteContent()
                val contentRef = FirebaseDatabase.getInstance().reference.child("Users").child(uid)
                val newDatas = Users(globalName,globalEmail,globalPass,editMbti,editLoc,editLike,true)
                contentRef.setValue(newDatas).addOnSuccessListener {
                    Toast.makeText(this, "회원정보가 수정되었습니다.", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { e ->
                    println("error: ${e.message}")
                    Toast.makeText(this, "회원정보 수정에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this, MyPage::class.java)
                startActivity(intent)
            }
        }
//        modificationButton.setOnClickListener {
//            val residenceT = residence.text.toString()
//            val mbtiT = mbti.text.toString()
//            val interestingT=interesting.text.toString()
//            val passT=pass.text.toString()
//
//            val result = Intent()
//            result.putExtra("residenceId", residenceT)
//            result.putExtra("mbtiId", mbtiT)
//            Log.d("HUN", "regidence:$residenceT, mbtiT:$mbtiT")
//            result.putExtra("interestingId", interestingT)
//            result.putExtra("passId", passT)
//            setResult(RESULT_OK, result)
//
//            finish()
//        }
    }

    private fun onDeleteContent() {
        val contentRef = FirebaseDatabase.getInstance().reference.child("Users").child(uid)
        contentRef.removeValue().addOnSuccessListener {
//            Toast.makeText(this, "삭제 성공", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            println("error: ${e.message}")
//            Toast.makeText(this, "삭제 실패", Toast.LENGTH_SHORT).show()
        }
    }
}