package com.example.biblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.biblioteca.database.daos.AuthorBookJoinDAO
import com.example.biblioteca.database.entities.*
import com.example.biblioteca.model.BookViewModel
import kotlinx.android.synthetic.main.activity_new_book.*


class NewBookActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var book : Book
    private lateinit var authorBook : AuthorBookJoin
    private lateinit var tagBook : TagBookJoin
    private lateinit var author : Author
    private lateinit var tag : Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        bt_add.setOnClickListener {
            //val authors = bookViewModel.getAllAuthor.value?.size!!.plus(1)
            //val tags = bookViewModel.getAllTag.value?.size!!.plus(1)
            book = Book(et_isbn.text.toString(),
                et_tittle.text.toString(),
                et_editorial.text.toString(),
                //TODO: Yo queria solo mandar a llamar el total de la lista authors para solo sumarle 1 y que ese sea el nuevo id de mi Author y Tag
                "cover_image",
                et_summary.text.toString(),
                false
            )
            author = Author(3,et_author.text.toString())
            tag = Tag(10,et_tag.text.toString())
            //authorBook = AuthorBookJoin(5,et_isbn.text.toString())
            //tagBook = TagBookJoin(11,et_isbn.text.toString())

            bookViewModel.insertBook(book)
            bookViewModel.insertAuthor(author)
            bookViewModel.insertTag(tag)
            //bookViewModel.insertAuthorBook(authorBook)
            //bookViewModel.insertTagBook(tagBook)
        }
    }
}
