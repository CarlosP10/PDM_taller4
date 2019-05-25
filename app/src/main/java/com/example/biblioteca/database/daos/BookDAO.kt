package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.biblioteca.database.entities.Book

@Dao
interface BookDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM books")
    fun deleteAll()

    @Query("SELECT * FROM books ORDER BY book_name ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE favorite = 1 ORDER BY book_name ASC")
    fun getAllFavBooks(): LiveData<List<Book>>

    @Query("UPDATE books SET favorite = NOT (SELECT favorite FROM books WHERE ISBM =:isbm) WHERE ISBM =:isbm")
    suspend fun updateFavBook(isbm: String)

    @Query("""SELECT ISBM, book_name, editorial, cover, summary, favorite
        FROM books
        INNER JOIN author_book_join ON books.ISBM = author_book_join.bookId
        INNER JOIN authors ON authors.id = author_book_join.authorId
        INNER JOIN tag_book_join ON books.ISBM = tag_book_join.bookId
        INNER JOIN tags ON tags.id = tag_book_join.tagId
        WHERE books.ISBM LIKE :text OR books.book_name LIKE :text OR authors.author_name LIKE :text OR  tags.tag_name LIKE :text
        GROUP BY ISBM""")
    fun getBooksByText(text: String): LiveData<List<Book>>
}