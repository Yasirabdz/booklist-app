package com.example.booklist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rvBooks: RecyclerView
    private val list = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProfile: ImageButton = findViewById(R.id.btn_profile)
        btnProfile.setOnClickListener(this)

        rvBooks = findViewById(R.id.rv_books)
        rvBooks.setHasFixedSize(true)

        list.addAll(getListBooks())
        showRecyclerList()
    }

    private fun getListBooks(): ArrayList<Book> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataHistory = resources.getStringArray(R.array.data_synopsis)
        val listBook = ArrayList<Book>()
        for (i in dataName.indices) {
            val book = Book(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataHistory[i])
            listBook.add(book)
        }
        return listBook
    }

    private fun showRecyclerList() {
        rvBooks.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListBookAdapter(list)
        rvBooks.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                showSelectedBook(data)
                val moveDetail = Intent(this@MainActivity, DetailActivity::class.java)
                moveDetail.putExtra(DetailActivity.EXTRA_BOOK, data)
                startActivity(moveDetail)
            }
        })
    }

    private fun showSelectedBook(book: Book) {
        Toast.makeText(this, "Kamu memilih " + book.name, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_profile -> {
                val moveAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveAbout)
            }
        }
    }
}