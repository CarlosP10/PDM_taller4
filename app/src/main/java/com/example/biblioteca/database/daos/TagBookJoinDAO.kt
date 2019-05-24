package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Tag
import com.example.biblioteca.database.entities.TagBookJoin

@Dao
interface TagBookJoinDAO  {
    @Insert

    fun insert(tagBookJoin: TagBookJoin)

    @Query("""
           SELECT * FROM books
           INNER JOIN tag_book_join
           ON books.ISBM = tag_book_join.bookId
           WHERE tag_book_join.tagId =:tagId
           """)
    fun getTagsForBook(tagId: Int): LiveData<List<Book>>

    @Query("""
           SELECT * FROM tags
           INNER JOIN tag_book_join
           ON tags.id = tag_book_join.tagId
           WHERE tag_book_join.bookId =:bookId
           """)
    fun getBooksForTag(bookId: String): LiveData<List<Tag>>

}