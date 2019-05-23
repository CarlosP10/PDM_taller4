package com.example.biblioteca.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
class Book(
    @PrimaryKey @ColumnInfo(name = "ISBM") val isbm : String,
    @ColumnInfo(name = "book_name") val bookName: String,
    @ColumnInfo(name = "editorial") val editorial: String,
    @ColumnInfo(name = "author") val author: Int,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean,
    @ColumnInfo(name = "tag") val tag: Int
)