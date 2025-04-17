package com.example.english_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.english_app.databinding.ActivitySelectDictionaryBinding
import com.example.english_app.databinding.ActivitySettingsBinding

class SelectDictionaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectDictionaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedLvl = intent.getStringExtra("EXTRA_KNOWLEDGE_LVL")

        with(binding) {
            ibCloseButtonSelectDictionary.setOnClickListener {
                val intent = Intent(this@SelectDictionaryActivity, SettigsActivity::class.java)
                startActivity(intent)
            }

            LVL.text = selectedLvl
        }



    }
}