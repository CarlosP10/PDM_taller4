package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.biblioteca.database.entities.Author

@Dao
interface AuthorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(author: Author)

    @Query("DELETE FROM authors")
    fun deleteAll()

    @Query("SELECT * FROM authors ORDER BY author_name ASC")
    fun getAllAuthors(): LiveData<List<Author>>

    @Query("SELECT * FROM authors WHERE id = :num")
    fun getAuthor(num: Int): Author
}