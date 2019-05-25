package com.example.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.biblioteca.database.daos.*
import com.example.biblioteca.database.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Author::class, Book::class, Tag::class, AuthorBookJoin::class, TagBookJoin::class], version = 2, exportSchema = false)
public abstract class LibraryRoomDatabase : RoomDatabase() {

    abstract fun authorDAO() : AuthorDAO

    abstract fun bookDAO() : BookDAO

    abstract fun tagDAO() : TagDAO

    abstract fun authorBookJoinDAO() : AuthorBookJoinDAO

    abstract fun tagBookJoinDAO() : TagBookJoinDAO

    companion object {
        @Volatile
        private var INSTANCE: LibraryRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LibraryRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, LibraryRoomDatabase::class.java, "Library_database")
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
                    authorBookPopulate(database.authorBookJoinDAO())
                    tagBookPopulate(database.tagBookJoinDAO())
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

            var book = Book("book1", "Historias de Terror", "Editorial en español", "cover_image", "Muchos cuentos de terror", false)
            bookDao.insert(book)
            book = Book("book2", "Game of Thrones", "Editorial en ingles", "cover_image", "Historia de fantasia", false)
            bookDao.insert(book)
            book = Book("book3", "Harry Potter", "Editorial en ingles", "cover_image", "Historia de fantasia y magia",false)
            bookDao.insert(book)
            book = Book("book4", "Aladdin", "Editorial de cuentos", "cover_image", "Historia de amor que toma lugar en las tierras arabes ", false)
            bookDao.insert(book)
            book = Book("book5", "Blanca Nieves", "Editorial de cuentos", "cover_image", "Historia de amor que toma lugar en un reino lejano",false)
            bookDao.insert(book)
            book = Book("book6", "Enciclopedia", "Editorial de Historia", "cover_image", "Grandes conocimientos en un libro", false)
            bookDao.insert(book)
            book = Book("book7", "The Lord of the Rings", "Editorial en ingles", "cover_image", "Historia que se desarrolla en la tercera edad del sol de la tierra media", false)
            bookDao.insert(book)
            book = Book("book8", "El diario de Ana Frank", "Editorial en español", "cover_image", "Relata su historia como adolescente y el tiempo de dos años cuando tuvo que ocultarse de los nazis en Ámsterdam", false)
            bookDao.insert(book)
            book = Book("book9", "La casa de los espiritus", "Editorial en español", "cover_image", "Relata la vida de la familia Trueba a lo largo de cuatro generaciones y sigue los movimientos sociales y políticos del período poscolonial de Chile",false)
            bookDao.insert(book)
            book = Book("book10", "El psicoanalista", "Editorial en español", "cover_image", "La historia, que pone a prueba la capacidad del protagonista para evitar su suicidio frente a la presión de un desconocido", false)
            bookDao.insert(book)
        }

        suspend fun authorBookPopulate(authorBookDao:AuthorBookJoinDAO){
            var authorBook = AuthorBookJoin(1,"book1")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(2,"book2")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(3,"book2")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(4,"book4")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(1,"book5")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(3,"book6")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(2,"book6")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(4,"book6")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(4,"book7")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(1,"book9")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(3,"book10")
            authorBookDao.insert(authorBook)
            authorBook = AuthorBookJoin(1,"book10")
            authorBookDao.insert(authorBook)
        }

        suspend fun tagBookPopulate(tagBookDao:TagBookJoinDAO){
            var tagBook = TagBookJoin(1,"book1")
            tagBookDao.insert(tagBook)

            tagBook = TagBookJoin(2,"book1")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(3,"book1")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(2,"book2")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(4,"book2")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(3,"book3")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(4,"book3")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(4,"book4")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(5,"book5")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(6,"book6")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(1,"book6")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(7,"book7")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(8,"book8")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(9,"book9")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(6,"book9")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(10,"book9")
            tagBookDao.insert(tagBook)
            tagBook = TagBookJoin(10,"book10")
            tagBookDao.insert(tagBook)
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