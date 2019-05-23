package com.example.biblioteca.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.AuthorBookJoin
import com.example.biblioteca.database.entities.Book

@Dao
interface AuthorBookJoinDAO  {
    @Insert
    fun insert(playlistSongJoin: AuthorBookJoin)

    @Query("""
           SELECT * FROM books
           INNER JOIN author_book_join
           ON books.ISBM = author_book_join.bookId
           WHERE author_book_join.authorId =:authorId
           """)
    fun getBooksForAuthor(authorId: Int): Array<Author>

    @Query("""
           SELECT * FROM authors
           INNER JOIN author_book_join
           ON authors.id=author_book_join.authorId
           WHERE author_book_join.bookId =:bookId
           """)
    fun getAuthorsForBook(bookId: String): Array<Book>

}