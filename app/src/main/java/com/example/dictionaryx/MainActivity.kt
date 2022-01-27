package com.example.dictionaryx


import android.app.DownloadManager
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.reflect.Method
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.WindowManager
import androidx.appcompat.app.ActionBar


class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define ActionBar object
        // Define ActionBar object
        val actionBar: ActionBar? = supportActionBar

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        val colorDrawable = ColorDrawable(Color.parseColor("#ffdd99"))

        // Set BackgroundDrawable

        // Set BackgroundDrawable
        actionBar?.setBackgroundDrawable(colorDrawable)


        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark)
        }



        val queue = Volley.newRequestQueue(this)

        find.setOnClickListener {
            val url = getUrl()

            val stringReq = StringRequest(Request.Method.GET, url,
                { response ->
                    try {
                        extractDefJason(response)
                    }
                    catch (exception:Exception){
                        exception.printStackTrace()
                    }
                },
                { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(stringReq)
        }
    }

    private fun getUrl(): String? {
        val word = worded.getText().toString()
        val apikey = "fbbbd1ed-9e52-41c1-a169-3e6bb3b5a66a"
        val url ="https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apikey"

        return url
    }

    private fun extractDefJason(response: String){
        val jasonArray = JSONArray(response)
        val firstIndex = jasonArray.getJSONObject(0)
        val getShortDef = firstIndex.getJSONArray("shortdef")
        val firstShortDef = getShortDef.get(0)

        val intent = Intent(this, WordDefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDef.toString())
        startActivity(intent)

    }
}