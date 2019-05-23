package com.example.biblioteca.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
class Author(
    @PrimaryKey @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "author_name") val authorName: String
)