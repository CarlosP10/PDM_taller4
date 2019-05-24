package com.example.biblioteca.interfaces

import com.example.biblioteca.database.entities.Book

interface FragmentCommunication {

    //El tipo de data debe ser Book, se ha dejado Int temporalmente
    fun sendData(data: Int)

}