package com.example.biblioteca.database.entities


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "author_book_join",
    primaryKeys = ["authorId", "bookId"],
    foreignKeys = arrayOf(
        ForeignKey(entity = Author::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("author_id"),
            onDelete = CASCADE),
        ForeignKey(entity = Book::class,
            parentColumns = arrayOf("ISBM"),
            childColumns = arrayOf("book_id"),
            onDelete = CASCADE)))

class AuthorBookJoin (
    val authorId: Int,
    val bookId: String
)