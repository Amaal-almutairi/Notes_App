package com.example.notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //Declaring the UI elements.
    lateinit var addNote: EditText
    lateinit var btnsub: Button
    lateinit var Notes: ArrayList<String>
    lateinit var myadap: RecyclerView
    var note = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        //Initialising the UI elements and use it
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // create an object for DBHlp so we can access to AddNotes function
        var DB = DBHLP(this)
        addNote = findViewById(R.id.edtext)
        btnsub = findViewById(R.id.b1)
        myadap = findViewById(R.id.rvmain)
        // show the data in database
        //  Retrieving the data from database and store it in array (Notes)
        Notes = DB.retrive()
       // defined the adapter
        myadap.adapter = myadap(Notes)
        myadap.layoutManager = LinearLayoutManager(this)


        myadap.adapter?.notifyDataSetChanged()
        // create setOnClickListener
        btnsub.setOnClickListener {
            note = addNote.text.toString()

            // her we can access to this function and  return Note,
            // add  data in array (note ) and save it in database
            DB.AddNotes(note)
            // last item added in array (note) will display in recycler view also the notes we  added before in database it will display
            Notes.add(note)
            addNote.text.clear()
            addNote.clearFocus()
            // update the data in recycler view
            myadap.adapter?.notifyDataSetChanged()

            // create Toast message to show this data is saved
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()


        }


    }
}