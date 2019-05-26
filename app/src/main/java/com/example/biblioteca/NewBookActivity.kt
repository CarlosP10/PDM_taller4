package com.example.biblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biblioteca.database.daos.AuthorBookJoinDAO
import com.example.biblioteca.database.entities.*
import com.example.biblioteca.model.BookViewModel
import kotlinx.android.synthetic.main.activity_new_book.*
import java.lang.Exception


class NewBookActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var book : Book
    private lateinit var authorScrollView : TextView
    private lateinit var tagsScrollView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        authorScrollView = findViewById(R.id.sv_authors)
        tagsScrollView = findViewById(R.id.sv_tags)

        bt_add.setOnClickListener {

            var bookSuccess = true
            //Se crea el libro a guardar

            book = Book(et_isbn.text.toString(),
                et_tittle.text.toString(),
                et_editorial.text.toString(),
                "cover_image",
                et_summary.text.toString(),
                false
            )

            //Se inserta el libro para asegurarnos que ya exista el ISBN que se usa para las tablas cruz.
            try {
                bookViewModel.insertBook(book)
            }catch (e: Exception){
                //TODO Aqui notificar al usuario que el ID del libro ya existe.
                bookSuccess = false
                println("El ISBN ya ha sido asignado.")
            }

            // Inserción de autores en tabla cruz

            //Se toma el texto en el EditText
            val authorInput = et_author.text.toString()
            //Se separan los IDs por comas
            val authorArray = authorInput.split(",")

            //Se recorre el arreglo
            for (i in 0..(authorArray.size-1)){
                //Este try es para recoger errores como que se ingrese texto en lugar de enteros o que no exista el autor especificado.
                try {
                    //Creo el elemento de la tabla cruz a insertar (recojo un elemento del arreglo, le hago trim para quitar
                    // los espacios a los lados del String y lo convierto a Int, luego tomo el ISBN del libro)
                    val auth = AuthorBookJoin(authorArray.get(i).trim().toInt(), et_isbn.text.toString())
                    //El if que esta comentariado es el error de que no se puede acceder a la base de datos desde el main thread
                    //En sí no importa porque es una validación que exista el autor antes de insertar, pero si no existe
                    //solo inserta y el Try Catch se encarga de no crashear la app, así que no se guarda el insert y cumple la misma función.

                    //if(bookViewModel.getAuthor(authorArray.get(i).trim().toInt()).id != null){
                        //Se inserta el autor
                        bookViewModel.insertAuthorBook(auth)
                    //}
                }catch (e: Exception){
                    println("Error!!!!!!!!!!!!!!!!!!!!!!!")
                    println(e)
                }
            }

            // Inserción de etiquetas en tabla cruz
            //El mismo proceso de arriba.
            val tagInput = et_tag.text.toString()
            val tagArray = tagInput.split(",")

            for (i in 0..(tagArray.size-1)){
                try {
                    val tag =TagBookJoin(tagArray.get(i).trim().toInt(), et_isbn.text.toString())
                    //if(bookViewModel.getTag(tagArray.get(i).trim().toInt()) != null){
                        bookViewModel.insertTagBook(tag)
                    //}
                }catch (e: Exception){
                    println("Error!!!!!!!!!!!!!!!!!!!!!!!")
                    println(e)
                }
            }

            if(bookSuccess){

                et_author.text.clear()
                et_editorial.text.clear()
                et_isbn.text.clear()
                et_summary.text.clear()
                et_tag.text.clear()
                et_tittle.text.clear()

                Toast.makeText(this, "Se ha registrado el nuevo libro.", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this, "Ha ocurrido un problema, revise que el ISBM no esté registrado.", Toast.LENGTH_LONG).show()
            }
        }
        //val authors = bookViewModel.getAllAuthor()
    }

}
