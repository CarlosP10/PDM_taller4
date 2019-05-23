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
    //lateinit var repository: BookRepository


    //SE PUEDE USAR SOLO LATEINIT PERO LO HICE ASI SOLO PARA SEGUIMIENTO DEL LABO
    init{
        val bookDao = LibraryRoomDatabase.getDatabase(app).bookDAO()
        val authorDao = LibraryRoomDatabase.getDatabase(app).authorDAO()
        val tagDao = LibraryRoomDatabase.getDatabase(app).tagDAO()
        repository = BookRepository(bookDao,authorDao,tagDao)
    }

    //------------------------BOOK----------------------------------

    fun insertBook(book: Book)= viewModelScope.launch(Dispatchers.IO){
        repository.insertBook(book)
    }

    fun getAllBook(): LiveData<List<Book>> = repository.getAllBook()

    //------------------------AUTHOR----------------------------------

    fun insertAuthor(author: Author)= viewModelScope.launch(Dispatchers.IO){
        repository.insertAuthor(author)
    }

    fun getAllAuthor():LiveData<List<Author>> = repository.getAllAuthor()

    //------------------------TAG----------------------------------

    fun insertTag(tag: Tag)= viewModelScope.launch(Dispatchers.IO){
        repository.insertTag(tag)
    }

    fun getAllTag():LiveData<List<Tag>> = repository.getAllTag()

    fun nukeBook() = repository.nukeBook()
    fun nukeAuthor() = repository.nukeAuthor()
    fun nukeTag() = repository.nukeTag()


}