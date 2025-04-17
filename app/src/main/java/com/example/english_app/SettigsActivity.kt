package com.example.english_app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.english_app.databinding.ActivitySettingsBinding

class SettigsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var selectedLvl : String? = null

        with(binding) {

            tvBeginnerLvl.setOnClickListener{
                btnConfirm.isVisible = true
                selectLvl(tvBeginnerLvl)
                selectedLvl = "beginner"
            }

            tvIntermediateLvl.setOnClickListener{
                btnConfirm.isVisible = true
                selectLvl(tvIntermediateLvl)
                selectedLvl = "Intermediate"
            }

            tvAdvancedLvl.setOnClickListener{
                btnConfirm.isVisible = true
                selectLvl(tvAdvancedLvl)
                selectedLvl = "Advanced"
            }

            btnConfirm.setOnClickListener {
                selectedLvl?.let { lvl ->
                    val intent = Intent(this@SettigsActivity, SelectDictionaryActivity::class.java)
                    intent.putExtra("EXTRA_KNOWLEDGE_LVL", lvl)
                    startActivity(intent)
                }
            }
        }

    }

    private fun selectLvl (
        tvLvlVariant: TextView
    ) {
        with(binding) {
            if (tvLvlVariant == tvBeginnerLvl) {
                tvLvlVariant.background = ContextCompat.getDrawable(
                    this@SettigsActivity,
                    R.drawable.shape_lvl_variant_beginner
                )

                tvLvlVariant.setTextColor(
                    ContextCompat.getColor(
                        this@SettigsActivity,
                        R.color.white
                    )
                )

                makeLvlNeutral(tvIntermediateLvl, tvAdvancedLvl)

            } else if (tvLvlVariant == tvIntermediateLvl) {
                tvLvlVariant.background = ContextCompat.getDrawable(
                    this@SettigsActivity,
                    R.drawable.shape_lvl_variant_intermediate
                )

                tvLvlVariant.setTextColor(
                    ContextCompat.getColor(
                        this@SettigsActivity,
                        R.color.white
                    )
                )

                makeLvlNeutral(tvBeginnerLvl, tvAdvancedLvl)
            } else {
                tvLvlVariant.background = ContextCompat.getDrawable(
                    this@SettigsActivity,
                    R.drawable.shape_lvl_variant_advanced
                )

                tvLvlVariant.setTextColor(
                    ContextCompat.getColor(
                        this@SettigsActivity,
                        R.color.white
                    )
                )

                makeLvlNeutral(tvBeginnerLvl, tvIntermediateLvl)
            }
        }
    }


    private fun makeLvlNeutral(
        tvLvlVariant1: TextView,
        tvLvlVariant2: TextView
    ) {
        tvLvlVariant1.background = ContextCompat.getDrawable(
            this@SettigsActivity,
            R.drawable.shape_rounded_select_lvl_variants
        )

        tvLvlVariant1.setTextColor(
            ContextCompat.getColor(
                this@SettigsActivity,
                R.color.black
            )
        )

        tvLvlVariant2.background = ContextCompat.getDrawable(
            this@SettigsActivity,
            R.drawable.shape_rounded_select_lvl_variants
        )

        tvLvlVariant2.setTextColor(
            ContextCompat.getColor(
                this@SettigsActivity,
                R.color.black
            )
        )
    }
}