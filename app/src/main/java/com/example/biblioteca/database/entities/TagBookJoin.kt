package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "tag_book_join",
    primaryKeys = ["tagId", "bookId"],
    foreignKeys = arrayOf(
        ForeignKey(entity = Tag::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("tag_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = Book::class,
            parentColumns = arrayOf("ISBM"),
            childColumns = arrayOf("book_id"),
            onDelete = ForeignKey.CASCADE
        )
    ))
class TagBookJoin(
    var tagId: Int,
    var bookId: String
)