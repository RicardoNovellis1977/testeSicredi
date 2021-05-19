package com.example.testeeventos.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.testeeventos.R
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                jump(null)
            }
        }, 5000)
        val image = findViewById<ImageView>(R.id.imageSplash)
        val animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.animation_blink
        )
        image.startAnimation(animation)
    }

    fun jump(view: View?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}