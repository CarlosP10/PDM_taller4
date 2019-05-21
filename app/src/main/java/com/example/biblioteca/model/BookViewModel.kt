package com.example.biblioteca.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class BookViewModel (app:Application):AndroidViewModel(app){
    /*private val repository: nameRepo

    init{
        val bookDao: RoomDB.getInstance(app).repoDao
        repository = nameRepo(bookDao)
    }

    //------------------------BOOK----------------------------------

    fun insertBook(book: Book)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(book)
    }

    fun getAllBook():LiveData<List<Book>> = repository.getAllBook()

    //------------------------AUTHOR----------------------------------

    fun insertAuthor(author: Author)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(author)
    }

    fun getAllAuthor():LiveData<List<Author>> = repository.getAuthor()

    //------------------------TAG----------------------------------

    fun insertTag(tag: Tag)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(tag)
    }

    fun getAllTag():LiveData<List<Tag>> = repository.getAllTag()

    fun nukeAll() = repository.nuke()

    */
}