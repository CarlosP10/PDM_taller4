package com.example.biblioteca.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.biblioteca.database.daos.AuthorDAO
import com.example.biblioteca.database.daos.BookDAO
import com.example.biblioteca.database.daos.TagDAO
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Tag

class BookRepository(private val bookDAO: BookDAO, private val authorDAO: AuthorDAO, private val tagDAO: TagDAO){

    @WorkerThread
    suspend fun insertBook(repoBook:Book){bookDAO.insert(repoBook)}

    @WorkerThread
    suspend fun insertAuthor(repoAuthor:Author){authorDAO.insert(repoAuthor)}

    @WorkerThread
    suspend fun insertTag(repoTag:Tag){tagDAO.insert(repoTag)}

    fun getAllBook():LiveData<List<Book>> = bookDAO.getAllBooks()
    fun getAllAuthor():LiveData<List<Author>> = authorDAO.getAllAuthors()
    fun getAllTag():LiveData<List<Tag>> = tagDAO.getAllTags()

    fun nukeBook() = bookDAO.deleteAll()
    fun nukeTag() = tagDAO.deleteAll()
    fun nukeAuthor() = authorDAO.deleteAll()
}