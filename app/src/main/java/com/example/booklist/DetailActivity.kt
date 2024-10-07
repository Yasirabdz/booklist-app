package com.example.booklist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_BOOK = "extra_book"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btn_backhome: ImageButton = findViewById(R.id.btn_back)
        btn_backhome.setOnClickListener(this)

        val book = intent.getParcelableExtra<Book>(EXTRA_BOOK)
        if (book != null) {
            val tvName: TextView = findViewById(R.id.tv_book_name)
            tvName.text = book.name

            val tvDescription: TextView = findViewById(R.id.tv_book_description)
            tvDescription.text = book.description

            val imgPhoto: ImageView = findViewById(R.id.img_book_photo)
            imgPhoto.setImageResource(book.photo)

            val tvHistory: TextView = findViewById(R.id.tv_book_synopsis)
            tvHistory.text = book.synopsis
        }

        val btnShare: ImageButton = findViewById(R.id.action_share)
        btnShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Judul Buku : ${book?.name} \nDeskripsi : ${book?.description} \n\nSinopsis : ${book?.synopsis}")
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                val moveBackHome = Intent(this@DetailActivity, MainActivity::class.java)
                startActivity(moveBackHome)
            }
        }
    }
}