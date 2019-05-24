package com.example.biblioteca.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.biblioteca.database.daos.*
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.AuthorBookJoin
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Tag

class BookRepository(private val bookDAO: BookDAO,
                     private val authorDAO: AuthorDAO,
                     private val tagDAO: TagDAO,
                     private val authorBookDao: AuthorBookJoinDAO,
                     private val tagBookDao: TagBookJoinDAO){

    val getAllBook:LiveData<List<Book>> = bookDAO.getAllBooks()
    val getAllFavBook:LiveData<List<Book>> = bookDAO.getAllFavBooks()
    val getAllAuthor:LiveData<List<Author>> = authorDAO.getAllAuthors()
    val getAllTag:LiveData<List<Tag>> = tagDAO.getAllTags()

    fun getAllAuthorBook(isbm:String): LiveData<List<Author>> = authorBookDao.getAuthorsForBook(isbm)
    fun getAllTagsBook(isbm:String): LiveData<List<Tag>> = tagBookDao.getBooksForTag(isbm)

    @WorkerThread
    suspend fun insertBook(repoBook:Book){bookDAO.insert(repoBook)}

    @WorkerThread
    suspend fun insertAuthor(repoAuthor:Author){authorDAO.insert(repoAuthor)}

    @WorkerThread
    suspend fun insertTag(repoTag:Tag){tagDAO.insert(repoTag)}

    fun nukeBook() = bookDAO.deleteAll()
    fun nukeTag() = tagDAO.deleteAll()
    fun nukeAuthor() = authorDAO.deleteAll()

    @WorkerThread
    suspend fun updateFavBook(isbm: String) = bookDAO.updateFavBook(isbm)
}