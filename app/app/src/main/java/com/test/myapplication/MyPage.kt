package com.test.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_modify.*

class MyPage : AppCompatActivity() {

//    lateinit var slogun: TextView

//
//    lateinit var name: TextView
    lateinit var profileButton: ImageButton
//    lateinit var residence: TextView
//    lateinit var mbti: TextView
//    lateinit var pass: TextView
//    lateinit var interesting: TextView
    lateinit var editbutton: ImageButton
//    lateinit var nameIndex: TextView
    lateinit var globalEmail : String
    lateinit var globalName : String
    lateinit var globalPass : String


//    var startmodify =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                val data: Intent? = result.data
//                val residenceValue = data?.getStringExtra("residenceId")
//                val mbtiValue = data?.getStringExtra("mbtiId")
//                val passValue = data?.getStringExtra("passId")
//                val interestingValue = data?.getStringExtra("interestingId")
//
//
//                if (residenceValue != null && passValue != null && mbtiValue != null && interestingValue != null) {
//                    residence.text = residenceValue
//                    pass.text = passValue
//                    mbti.text = mbtiValue
//                    interesting.text = interestingValue
//
//
//                }
//
//            }
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

//        slogun = findViewById(R.id.slogun)

//        name = findViewById(R.id.name)
//        pass = findViewById(R.id.pass)
//        residence = findViewById(R.id.residence)
//        interesting = findViewById(R.id.interesting)
//        mbti = findViewById(R.id.mbti)

        //서버용
        val nameDB = findViewById<TextView>(R.id.name)
        val emailDB = findViewById<TextView>(R.id.id)
        val passDB = findViewById<TextView>(R.id.pass)
        val residenceDB = findViewById<TextView>(R.id.residence)
        val interestingDB = findViewById<TextView>(R.id.interesting)
        val mbtiDB = findViewById<TextView>(R.id.mbti)

        // get request
        val uid = FirebaseAuth.getInstance().uid ?:""
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val currentUserDB = myRef.child("Users").child(uid)

        Log.e("UID",currentUserDB.toString())
        currentUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nameDB.text = snapshot.child("name").value.toString()
                emailDB.text = snapshot.child("id").value.toString()
                passDB.text = snapshot.child("pw").value.toString()
                residenceDB.text = snapshot.child("loc").value.toString()
                interestingDB.text = snapshot.child("like").value.toString()
                mbtiDB.text = snapshot.child("mbti").value.toString()
                globalEmail = snapshot.child("id").value.toString()
                globalName = snapshot.child("name").value.toString()
                globalPass = snapshot.child("pw").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
//        nameIndex = findViewById(R.id.name)
//
//
//        val nameIndexText = nameIndex.text.toString()
//        val charFind = ','
//
//        val index = nameIndexText.indexOf(charFind)
//
//        val slogunText: String = slogun.text.toString()
//        val nameText: String = name.text.toString()
//        val builder = SpannableStringBuilder(slogunText)
//        val builder2 = SpannableStringBuilder(nameText)
//
//
//        val Wcolor = ForegroundColorSpan(Color.RED)
//        builder.setSpan(Wcolor, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        val ILcolor = ForegroundColorSpan(Color.RED)
//        builder.setSpan(ILcolor, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        val nameStyle = StyleSpan(Typeface.BOLD)
//        builder2.setSpan(nameStyle, 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//
//        val nameSize = RelativeSizeSpan(1.6f)
//        builder2.setSpan(nameSize, 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//
//        slogun.text = builder
//        name.text = builder2

        val logo = findViewById<ImageView>(R.id.logo)
        logo.setOnClickListener{
            val intent = Intent(this,Main::class.java)
            startActivity(intent)
        }

        editbutton = findViewById(R.id.editbutton)
        editbutton.setOnClickListener {
            val intent = Intent(this, Modify::class.java)
            intent.putExtra("name", globalName)
            intent.putExtra("email", globalEmail)
            intent.putExtra("pw", globalPass)
            startActivity(intent)
        }


        profileButton = findViewById(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            startActivity(intent)
        }

    }
}