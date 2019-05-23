package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.biblioteca.database.entities.Tag

@Dao
interface TagDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tag: Tag)

    @Query("DELETE FROM tags")
    fun deleteAll()

    @Query("SELECT * FROM tags ORDER BY tag_name ASC")
    fun getAllTags(): LiveData<List<Tag>>
}