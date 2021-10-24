package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //Declaring the UI elements.
    lateinit var addNote:EditText
    lateinit var btnsub:Button
    lateinit var Notes:ArrayList<String>
    lateinit var myadap: RecyclerView
    var note=""



    override fun onCreate(savedInstanceState: Bundle?) {
        //Initialising the UI elements and use it
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addNote = findViewById(R.id.edtext)
        btnsub = findViewById(R.id.b1)
        myadap = findViewById(R.id.rvmain)
        Notes = arrayListOf()
        myadap.adapter=myadap(Notes)
        myadap.layoutManager=LinearLayoutManager(this)
        // create setOnClickListener
        btnsub.setOnClickListener {
            note =addNote.text.toString()
            // create an object for DBHlp so we can access to AddNotes function
            var DB = DBHLP(applicationContext)
            // her we can access to this function and  return Note
            DB.AddNotes(note)
            addNote.text.clear()
            addNote.clearFocus()
            // create Toast message to show this data is saved
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()


        }


    }
}