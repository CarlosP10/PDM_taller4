package com.example.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.biblioteca.database.daos.AuthorDAO
import com.example.biblioteca.database.daos.BookDAO
import com.example.biblioteca.database.daos.TagDAO
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Author::class, Book::class, Tag::class], version = 1)
public abstract class LibraryRoomDatabase : RoomDatabase() {

    abstract fun authorDAO() : AuthorDAO

    abstract fun bookDAO() : BookDAO

    abstract fun tagDAO() : TagDAO

    companion object {
        private var INSTANCE: LibraryRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LibraryRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,//.applicationContext,
                    LibraryRoomDatabase::class.java,
                    "Library_database"
                )
                    .addCallback(LibraryDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class LibraryDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    authorPopulateDatabase(database.authorDAO())
                    bookPopulateDatabase(database.bookDAO())
                    tagPopulateDatabase(database.tagDAO())
                }
            }
        }

        suspend fun authorPopulateDatabase(authorDao: AuthorDAO) {
            authorDao.deleteAll()

            var author = Author(1, "Juan")
            authorDao.insert(author)
            author = Author(2, "Adrian")
            authorDao.insert(author)
            author = Author(3, "Paola")
            authorDao.insert(author)
            author = Author(4, "Danna")
            authorDao.insert(author)
        }

        suspend fun bookPopulateDatabase(bookDao: BookDAO) {
            bookDao.deleteAll()

            var book = Book("book1", "Historias de Terror", "Editorial en español", 3, "cover_image", "Muchos cuentos de terror", false, 2)
            bookDao.insert(book)
            book = Book("book2", "Game of Thrones", "Editorial en ingles", 1, "cover_image", "Historia de fantasia", false, 1)
            bookDao.insert(book)
            book = Book("book3", "Harry Potter", "Editorial en ingles", 2, "cover_image", "Historia de fantasia y magia",false, 1)
            bookDao.insert(book)
            book = Book("book4", "Aladdin", "Editorial de cuentos", 1, "cover_image", "Historia de amor que toma lugar en las tierras arabes ", false, 5)
            bookDao.insert(book)
            book = Book("book5", "Blanca Nieves", "Editorial de cuentos", 2, "cover_image", "Historia de amor que toma lugar en un reino lejano",false, 5)
            bookDao.insert(book)
            book = Book("book6", "Enciclopedia", "Editorial de Historia", 3, "cover_image", "Grandes conocimientos en un libro", false, 4)
            bookDao.insert(book)
            book = Book("book7", "The Lord of the Rings", "Editorial en ingles", 1, "cover_image", "Historia que se desarrolla en la tercera edad del sol de la tierra media", false, 1)
            bookDao.insert(book)
            book = Book("book8", "El diario de Ana Frank", "Editorial en español", 3, "cover_image", "Relata su historia como adolescente y el tiempo de dos años cuando tuvo que ocultarse de los nazis en Ámsterdam", false, 8)
            bookDao.insert(book)
            book = Book("book9", "La casa de los espiritus", "Editorial en español", 4, "cover_image", "Relata la vida de la familia Trueba a lo largo de cuatro generaciones y sigue los movimientos sociales y políticos del período poscolonial de Chile",false, 1 )
            bookDao.insert(book)
            book = Book("book10", "El psicoanalista", "Editorial en español", 4, "cover_image", "La historia, que pone a prueba la capacidad del protagonista para evitar su suicidio frente a la presión de un desconocido", false, 10)
            bookDao.insert(book)
        }

        suspend fun tagPopulateDatabase(tagDao: TagDAO){
            tagDao.deleteAll()

            var tag = Tag(1, "Fantasia")
            tagDao.insert(tag)
            tag = Tag(2, "Terror")
            tagDao.insert(tag)
            tag = Tag(3, "Comedia")
            tagDao.insert(tag)
            tag = Tag(4, "Historia")
            tagDao.insert(tag)
            tag = Tag(5, "Romance")
            tagDao.insert(tag)
            tag = Tag(6, "Policial")
            tagDao.insert(tag)
            tag = Tag(7, "Cientifico")
            tagDao.insert(tag)
            tag = Tag(8,  "Biografias")
            tagDao.insert(tag)
            tag = Tag(9, "Literatura")
            tagDao.insert(tag)
            tag = Tag(10, "Suspenso")
            tagDao.insert(tag)
        }
    }
}