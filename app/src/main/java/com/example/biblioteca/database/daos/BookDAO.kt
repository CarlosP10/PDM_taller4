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
}