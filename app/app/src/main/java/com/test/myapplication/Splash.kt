package com.test.myapplication

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.constraintlayout.widget.ConstraintLayout

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Creating an array of two colors
        val mColors = arrayOf(ColorDrawable(Color.WHITE), ColorDrawable(Color.BLACK))

        val handler = Handler()
        handler.postDelayed({
            val back = findViewById<ConstraintLayout>(R.id.splashBack)
//            back.setBackgroundResource(R.color.black)
            val mTransition = TransitionDrawable(mColors)
            back.background = mTransition
            mTransition.startTransition(2000)
        },1000)
        handler.postDelayed({
            val intent = Intent(baseContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}