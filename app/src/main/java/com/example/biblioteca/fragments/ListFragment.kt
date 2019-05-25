package com.example.biblioteca.fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.R
import com.example.biblioteca.adapters.BookAdapter
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.interfaces.FragmentCommunication
import com.example.biblioteca.model.BookViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.list_item.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var action: View.OnClickListener
    private lateinit var actionFav: View.OnClickListener

    private lateinit var bookViewModel: BookViewModel

    private lateinit var activity: Activity
    private lateinit var comunicacion: FragmentCommunication

    private lateinit var viewAdapter:BookAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var bookList: List<Book>

    private var filter = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_list, container, false)
        return vista
    }

    override fun onStart() {
        super.onStart()

        action = View.OnClickListener { v ->
            comunicacion.sendData(booksRV.getChildAdapterPosition(v))
        }

        actionFav = View.OnClickListener { v ->
            updateFav(v.tag.toString())
            println("Favorito modificado: "+v.tag.toString())
        }


        viewAdapter = BookAdapter(listOf(Book("", "", "", "", "", false)), action, actionFav)

        bookViewModel.getAllBook.observe(this, Observer { books ->
            books?.let {

                bookList = it

                viewAdapter.setData(bookList)

            }
        })

        initRecycler()

        checkBoxFav.setOnClickListener{ v ->
            if(checkBoxFav.isChecked){
                println("FAVS")
                bookViewModel.changeBookList("fav")
                //TODO Seleccionar favoritos

            }else{
                bookViewModel.changeBookList("all")
                //TODO Seleccionar todos
            }
        }

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                filter = s.toString()
                if(filter.isNotBlank() && filter.isNotEmpty()){
                    bookViewModel.setBooksByText("%"+filter+"%")
                }

            }

        })


    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity){
            this.activity = context
            comunicacion = this.activity as FragmentCommunication
        }

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun updateFav(isbm: String){
        bookViewModel.updateFavBook(isbm)
    }

    fun initRecycler(){

        viewManager = LinearLayoutManager(context)

        with(booksRV){
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }

    }
}
