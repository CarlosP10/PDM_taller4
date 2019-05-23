package com.example.biblioteca.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.R
import kotlinx.android.synthetic.main.list_item.view.*

class BookAdapter(var items: List<Int>): RecyclerView.Adapter<BookAdapter.BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return BookHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {

        holder.bind(items[position])

    }

    class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item: Int) = with(itemView){
            rv_bookName.text = "Book #"+item
        }

    }
}