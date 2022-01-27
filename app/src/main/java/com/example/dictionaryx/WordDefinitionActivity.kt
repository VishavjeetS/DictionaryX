package com.example.dictionaryx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_word_definition.*

class WordDefinitionActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definition)

        def_textv.text = intent.getStringExtra(KEY)

        back_image_view.setOnClickListener {
            finish()
        }
    }
}