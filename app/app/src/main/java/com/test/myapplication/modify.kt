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
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class modify : AppCompatActivity() {
    lateinit var mypageButton: ImageButton
    lateinit var profileButton: ImageButton
    lateinit var residence: EditText
    lateinit var mbti: EditText
    lateinit var pass: EditText
    lateinit var interesting: EditText
    lateinit var modificationButton: ImageButton
    lateinit var slogun: TextView
    lateinit var name: TextView
    lateinit var nameIndex: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify )

        slogun=findViewById(R.id.slogun)
        name=findViewById(R.id.name)

        val slogunText:String=slogun.text.toString()
        val nameText:String=name.text.toString()
        val builder= SpannableStringBuilder(slogunText)
        val builder2= SpannableStringBuilder(nameText)
        nameIndex=findViewById(R.id.name)

        val nameIndexText=nameIndex.text.toString()
        val charFind=','

        val index=nameIndexText.indexOf(charFind)


        val Wcolor= ForegroundColorSpan(Color.RED)
        builder.setSpan(Wcolor, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val ILcolor= ForegroundColorSpan(Color.RED)
        builder.setSpan(ILcolor, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val nameStyle= StyleSpan(Typeface.BOLD)
        builder2.setSpan(nameStyle,0,index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val nameSize= RelativeSizeSpan(1.6f)
        builder2.setSpan(nameSize,0,index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        slogun.text = builder
        name.text=builder2
        profileButton=findViewById(R.id.profileButton)
        residence=findViewById(R.id.residence)
        mbti=findViewById(R.id.mbti)
        pass=findViewById(R.id.pass)
        interesting=findViewById(R.id.interesting)
        profileButton.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }




        mypageButton=findViewById(R.id.profileButton)
        mypageButton.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        modificationButton = findViewById(R.id.modificationButton)
        modificationButton.setOnClickListener {
            val residenceT = residence.text.toString()
            val mbtiT = mbti.text.toString()
            val interestingT=interesting.text.toString()
            val passT=pass.text.toString()

            val result = Intent()
            result.putExtra("residenceId", residenceT)
            result.putExtra("mbtiId", mbtiT)
            Log.d("HUN", "regidence:$residenceT, mbtiT:$mbtiT")
            result.putExtra("interestingId", interestingT)
            result.putExtra("passId", passT)
            setResult(RESULT_OK, result)

            finish()
        }
    }
}