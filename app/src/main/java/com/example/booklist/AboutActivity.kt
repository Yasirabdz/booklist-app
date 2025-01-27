package com.example.booklist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val btnBackHome: ImageButton = findViewById(R.id.btn_back)
        btnBackHome.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                val moveHomepage = Intent(this@AboutActivity, MainActivity::class.java)
                startActivity(moveHomepage)
            }
        }
    }
}