package com.example.biblioteca

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.fragments.DetailFragment
import com.example.biblioteca.fragments.ListFragment
import com.example.biblioteca.interfaces.FragmentCommunication

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.nio.file.WatchEvent

class MainActivity : AppCompatActivity(), ListFragment.OnFragmentInteractionListener, DetailFragment.OnFragmentInteractionListener, FragmentCommunication {

    override fun sendData(data: Int) {
        var detalle = DetailFragment()
        var datos = Bundle()
        datos.putInt("BookID", data)
        detalle?.arguments = datos

        if(detailContainer != null){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.detailContainer, detalle)
                    .addToBackStack("prev")
                    .commit()
        }else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, detalle)
                    .addToBackStack("prev")
                    .commit()
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var listFragment = ListFragment()


        supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, listFragment)
                .commit()


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onStart() {
        super.onStart()

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                //TODO: Actualizar RecyclerView al detectar cambios en el texto
                Toast.makeText(this@MainActivity, s, Toast.LENGTH_SHORT).show()

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
