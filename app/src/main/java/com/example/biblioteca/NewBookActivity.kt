package com.example.biblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.model.BookViewModel
import kotlinx.android.synthetic.main.activity_new_book.*

class NewBookActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var book : Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        bt_add.setOnClickListener {
            book = Book(et_isbn.text.toString(),
                et_tittle.text.toString(),
                et_editorial.text.toString(),
                3,
                "cover_image",
                et_summary.text.toString(),
                false,
                3
            )
            bookViewModel.insertBook(book)
        }


    }
}
