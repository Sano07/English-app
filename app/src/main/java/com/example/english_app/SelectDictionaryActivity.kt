package com.example.english_app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.english_app.databinding.ActivitySelectDictionaryBinding
import com.example.english_app.databinding.ActivitySettingsBinding
import java.util.ArrayList

class SelectDictionaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectDictionaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedLvl = intent.getStringExtra("EXTRA_KNOWLEDGE_LVL")

        var selectedDictionary : MutableList<String> = mutableListOf()

        with(binding) {
            ibCloseButtonSelectDictionary.setOnClickListener {
                val intent = Intent(this@SelectDictionaryActivity, SettigsActivity::class.java)
                startActivity(intent)
            }

            btnConfirm.setOnClickListener {
                selectedDictionary.let { dictionaryList ->
                    val intent = Intent(this@SelectDictionaryActivity, MainActivity::class.java)
                    intent.putStringArrayListExtra("EXTRA_DICTIONARY_LIST", ArrayList(dictionaryList) )
                    intent.putExtra("EXTRA_SELECTED_LVL", selectedLvl)
                    startActivity(intent)
                }
            }

            LVL.text = selectedLvl
            if (selectedLvl == "Beginner") {
                LVL.setTextColor(
                    ContextCompat.getColor(
                        this@SelectDictionaryActivity,
                        R.color.Beginner
                    )
                )
            } else if (selectedLvl == "Intermediate") {
                LVL.setTextColor(
                    ContextCompat.getColor(
                        this@SelectDictionaryActivity,
                        R.color.Intermediate
                    )
                )
            } else {
                LVL.setTextColor(
                    ContextCompat.getColor(
                        this@SelectDictionaryActivity,
                        R.color.Advanced
                    )
                )
            }

        }

        val listOfThemes = listOf(
            findViewById<TextView>(R.id.tvTheme1),
            findViewById(R.id.tvTheme2),
            findViewById(R.id.tvTheme3),
            findViewById(R.id.tvTheme4),
            findViewById(R.id.tvTheme5),
            findViewById(R.id.tvTheme6),
            findViewById(R.id.tvTheme7),
            findViewById(R.id.tvTheme8),
            findViewById(R.id.tvTheme9),
            findViewById(R.id.tvTheme10)
        )

        for (textView in listOfThemes) {
            textView.setOnClickListener {
                val theme = textView.text.toString()

                if (selectedDictionary.contains(theme)) {
                    selectedDictionary.remove(theme)
                    textView.background = ContextCompat.getDrawable(
                        this@SelectDictionaryActivity,
                        R.drawable.shape_rounded_select_lvl_variants
                    )
                    textView.setTextColor(
                        ContextCompat.getColor(
                            this@SelectDictionaryActivity,
                            R.color.black
                        )
                    )
                } else {
                    if (selectedDictionary.size < 3) {
                        selectedDictionary.add(theme)
                        textView.background = ContextCompat.getDrawable(
                            this@SelectDictionaryActivity,
                            R.drawable.shape_selected_themes
                        )

                        textView.setTextColor(
                            ContextCompat.getColor(
                                this@SelectDictionaryActivity,
                                R.color.white
                            )
                        )
                    }
                }
            }
        }
    }


}