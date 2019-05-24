package com.example.biblioteca.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.biblioteca.database.LibraryRoomDatabase
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Tag
import com.example.biblioteca.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel (app:Application):AndroidViewModel(app){
    private val repository: BookRepository
    private lateinit var scope: CoroutineScope
    val getAllBook: LiveData<List<Book>>
    val getAllAuthor:LiveData<List<Author>>
    val getAllTag:LiveData<List<Tag>>
    //lateinit var repository: BookRepository



    init{
        val bookDao = LibraryRoomDatabase.getDatabase(app, scope).bookDAO()
        val authorDao = LibraryRoomDatabase.getDatabase(app, scope).authorDAO()
        val tagDao = LibraryRoomDatabase.getDatabase(app, scope).tagDAO()
        repository = BookRepository(bookDao,authorDao,tagDao)
        getAllBook = repository.getAllBook
        getAllAuthor = repository.getAllAuthor
        getAllTag = repository.getAllTag
    }

    fun insertBook(book: Book)= viewModelScope.launch(Dispatchers.IO){
        repository.insertBook(book)
    }

    fun insertAuthor(author: Author)= viewModelScope.launch(Dispatchers.IO){
        repository.insertAuthor(author)
    }

    fun insertTag(tag: Tag)= viewModelScope.launch(Dispatchers.IO){
        repository.insertTag(tag)
    }

    fun nukeBook() = repository.nukeBook()
    fun nukeAuthor() = repository.nukeAuthor()
    fun nukeTag() = repository.nukeTag()


}