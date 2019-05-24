package com.example.biblioteca.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.R
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.interfaces.RecyclerViewClickListener
import kotlinx.android.synthetic.main.list_item.view.*

class BookAdapter(var items: List<Book>, mListener: View.OnClickListener, mFavListener: View.OnClickListener): RecyclerView.Adapter<BookAdapter.BookHolder>() {

    private lateinit var action: View.OnClickListener
    private lateinit var actionFav: View.OnClickListener

    init {
        action = mListener
        actionFav = mFavListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        var holder = BookHolder(view)
        holder.onClick(action)
        holder.onFavClick(actionFav)

        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {

        holder.bind(items[position])

    }

    class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView), RecyclerViewClickListener{

        override fun onFavClick(listener: View.OnClickListener) {
            itemView.btn_fav.setOnClickListener(listener)
        }

        override fun onClick(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }

        fun bind(item: Book) = with(itemView){
            rv_bookName.text = item.bookName
            btn_fav.tag = item.isbm
            if (item.favorite){
                btn_fav.background = ContextCompat.getDrawable(context, R.drawable.btn_bg_fav)
                btn_fav.text = "X"
            }
        }

    }
}