package com.example.english_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        lifecycleScope.launch {
            delay(10000)
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            finish()
        }

        Glide.with(this)
            .asGif()
            .load(R.drawable.gif_time_to_learn) // или URL
            .into(findViewById(R.id.ivWelcomeGif))
    }
}