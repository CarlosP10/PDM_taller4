package com.example.biblioteca.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
class Tag(
    @PrimaryKey @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "tag_name") val tagName: String
)