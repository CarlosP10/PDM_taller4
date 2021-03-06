package com.example.biblioteca.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.biblioteca.database.LibraryRoomDatabase
import com.example.biblioteca.database.daos.AuthorBookJoinDAO
import com.example.biblioteca.database.entities.*
import com.example.biblioteca.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel (app:Application):AndroidViewModel(app){
    val repository: BookRepository
    //private lateinit var scope: CoroutineScope
    var getAllBook: LiveData<List<Book>>
    var getAllFavs: LiveData<List<Book>>
    var getAllSpecific: LiveData<List<Book>>
    val getAllAuthor:LiveData<List<Author>>
    val getAllTag:LiveData<List<Tag>>



    init{
        val bookDao = LibraryRoomDatabase.getDatabase(app, viewModelScope).bookDAO()
        val authorDao = LibraryRoomDatabase.getDatabase(app, viewModelScope).authorDAO()
        val tagDao = LibraryRoomDatabase.getDatabase(app, viewModelScope).tagDAO()
        val authorForBookDao = LibraryRoomDatabase.getDatabase(app, viewModelScope).authorBookJoinDAO()
        val tagForBookDao = LibraryRoomDatabase.getDatabase(app, viewModelScope).tagBookJoinDAO()
        repository = BookRepository(bookDao,authorDao,tagDao,authorForBookDao,tagForBookDao)
        getAllBook = repository.getAllBook
        getAllFavs = repository.getAllFavBook
        getAllSpecific = repository.getAllBook
        getAllAuthor = repository.getAllAuthor
        getAllTag = repository.getAllTag

    }

    fun changeBookList(opc: String){
        getAllBook = when(opc){
            "all" -> repository.getAllBook
            "fav" -> repository.getAllFavBook
            else -> repository.getAllBook
        }
    }

    fun getBooksByText(text: String) = repository.getBooksByText(text)

    fun getAuthor(num: Int) = repository.getAuthor(num)

    fun getTag(num: Int) = repository.getTag(num)

    fun setBooksByText(text: String){
        getAllSpecific = getBooksByText(text)
    }

    fun updateFavBook(isbm: String) = viewModelScope.launch(Dispatchers.IO){ repository.updateFavBook(isbm) }

    fun getAuthorsbyBook(isbm:String)= repository.getAllAuthorBook(isbm)

    fun getTagsbyBook(isbm:String)= repository.getAllTagsBook(isbm)

    fun insertBook(book: Book)= viewModelScope.launch(Dispatchers.IO){
        repository.insertBook(book)
    }

    fun insertAuthor(author: Author)= viewModelScope.launch(Dispatchers.IO){
        repository.insertAuthor(author)
    }

    fun insertTag(tag: Tag)= viewModelScope.launch(Dispatchers.IO){
        repository.insertTag(tag)
    }

    fun insertAuthorBook(authorBook: AuthorBookJoin)= viewModelScope.launch(Dispatchers.IO){
        repository.insertAuthorBook(authorBook)
    }

    fun insertTagBook(tagBook: TagBookJoin)= viewModelScope.launch(Dispatchers.IO){
        repository.insertTagBook(tagBook)
    }

    fun nukeBook() = repository.nukeBook()
    fun nukeAuthor() = repository.nukeAuthor()
    fun nukeTag() = repository.nukeTag()

}