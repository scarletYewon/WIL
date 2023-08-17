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
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var slogun: TextView
    lateinit var name: TextView
    lateinit var profileButton: ImageButton
    lateinit var residence: TextView
    lateinit var mbti: TextView
    lateinit var pass: TextView
    lateinit var interesting: TextView
    lateinit var editbutton: ImageButton
    lateinit var nameIndex: TextView
    var startmodify =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val residenceValue = data?.getStringExtra("residenceId")
                val mbtiValue = data?.getStringExtra("mbtiId")
                val passValue = data?.getStringExtra("passId")
                val interestingValue = data?.getStringExtra("interestingId")


                if (residenceValue != null && passValue != null && mbtiValue != null && interestingValue != null) {
                    residence.text = residenceValue
                    pass.text = passValue
                    mbti.text = mbtiValue
                    interesting.text = interestingValue


                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editbutton = findViewById(R.id.editbutton)
        editbutton.setOnClickListener {
            startmodify.launch(Intent(this, modify::class.java))
        }

        slogun = findViewById(R.id.slogun)
        name = findViewById(R.id.name)
        residence = findViewById(R.id.residence)
        mbti = findViewById(R.id.mbti)
        pass = findViewById(R.id.pass)
        interesting = findViewById(R.id.interesting)
        nameIndex = findViewById(R.id.name)

        val nameIndexText = nameIndex.text.toString()
        val charFind = ','

        val index = nameIndexText.indexOf(charFind)

        val slogunText: String = slogun.text.toString()
        val nameText: String = name.text.toString()
        val builder = SpannableStringBuilder(slogunText)
        val builder2 = SpannableStringBuilder(nameText)


        val Wcolor = ForegroundColorSpan(Color.RED)
        builder.setSpan(Wcolor, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val ILcolor = ForegroundColorSpan(Color.RED)
        builder.setSpan(ILcolor, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val nameStyle = StyleSpan(Typeface.BOLD)
        builder2.setSpan(nameStyle, 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        val nameSize = RelativeSizeSpan(1.6f)
        builder2.setSpan(nameSize, 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        slogun.text = builder
        name.text = builder2

        profileButton = findViewById(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}