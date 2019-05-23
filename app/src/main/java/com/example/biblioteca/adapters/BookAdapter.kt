package com.example.biblioteca.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.R
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.interfaces.RecyclerViewClickListener
import kotlinx.android.synthetic.main.list_item.view.*

class BookAdapter(var items: List<Book>, mListener: View.OnClickListener): RecyclerView.Adapter<BookAdapter.BookHolder>() {

    private lateinit var action: View.OnClickListener

    init {
        action = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        var holder = BookHolder(view)
        holder.onClick(action)

        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {

        holder.bind(items[position])

    }

    class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView), RecyclerViewClickListener{


        override fun onClick(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }

        fun bind(item: Book) = with(itemView){
            rv_bookName.text = item.bookName
        }

    }
}