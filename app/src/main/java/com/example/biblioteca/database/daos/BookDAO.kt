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
}