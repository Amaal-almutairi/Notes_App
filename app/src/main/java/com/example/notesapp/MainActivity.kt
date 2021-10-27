package com.example.notesapp

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    lateinit var DB:DBHLP



    override fun onCreate(savedInstanceState: Bundle?) {
        //Initialising the UI elements and use it
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // create an object for DBHlp so we can access to AddNotes function
         DB = DBHLP(this)
        addNote = findViewById(R.id.edtext)
        btnsub = findViewById(R.id.b1)
        myadap = findViewById(R.id.rvmain)
        // show the data in database
        //  Retrieving the data from database and store it in array (Notes)
        Notes = DB.retrive()
        // this function will update the recycler view
        updtRC()



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
            // this function will update the recycler view
            updtRC()

            // create Toast message to show this data is saved
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()

        }}
    // we will replace old value with new one

        fun edit(oldnote:String, Upnote: String){
            // her we will update the value in database
            DB.updatNote(oldnote,Upnote)
            // this function will update the recycler view
            updtRC()
        }

        fun delete(msg:String){
            // her will delete the value in database
            DB.deleteNote(msg)
         // this function will update the recycler view
            updtRC()


        }
// thie will ahow the alertdialog with edittext on it
    // the value inside the edittext will replace with new one
        fun openwendow(oldnote: String){
            val dialog=AlertDialog.Builder(this)
            val newNote=EditText(this)
            newNote.hint="Enter new text"
            dialog.setCancelable(false).setPositiveButton("Save",DialogInterface.OnClickListener {
                    _, i -> edit(oldnote,newNote.text.toString())})

            dialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, i ->  })
    // the alert will create if we press the edit icon the function edit will cal the updatNote function
    // in database so we can make change in database
            val Alert = dialog.create()
            Alert.setTitle("Update Note")
            // setView will show the value in edittext inside alert
            Alert.setView(newNote)
            Alert.show()


        }

    fun updtRC(){
        myadap.adapter = myadap(this,DB.retrive())
        myadap.layoutManager = LinearLayoutManager(this)
    }



}