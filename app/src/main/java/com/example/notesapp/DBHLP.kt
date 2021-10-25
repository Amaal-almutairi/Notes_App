package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



// her we call SQLiteOpenHelper Library context means the constructor , name "means the file name ", factory
// for now is null and version is the upgrade
//in this class  we implement two functions onCreate and onUpgrade
// class DBHlp inherent with SQLiteOpenHelper

class DBHLP (context: Context): SQLiteOpenHelper(context,"Notes.db", null,1 ) {
    var SQLitD:SQLiteDatabase=writableDatabase
    // create variable to hold the SQLiteDatabase (it's constant word) then we Initialising to writableDatabase

    override fun onCreate(DP: SQLiteDatabase?) {
        if (DP != null) {
            // we need to check if db is not null
            // column name, type is text
           DP.execSQL("create table AddNotes (Note Text)")
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    // create function with one  parameter to save data
    fun AddNotes(Note:String){
        // create variable to enter the data to database
        val CV = ContentValues()
        // her we will enter the name for AddNotes and the location in what row the data enter and saved?
        CV.put("Note", Note)

        // her we will enter the Notes for AddNotes , AddNotes is table's Note and
        // the last parameters is ContentValues
        SQLitD.insert("AddNotes ",null , CV )
    }


    // this function will receive the name we saved it before and will return String
    @SuppressLint("Range")
    fun retrive():ArrayList<String>{
        // create a variable holds array to get the data from user
        var Not1 = arrayListOf<String>()
        // we create variable to store the notes from AddNotes' table
        // we call SQLitD to search from the table
        var c: Cursor = SQLitD.query("AddNotes",null,null, null,null,null,null )
        // her we ask the object c to search from the first column
       if ( c.moveToFirst()){
           // we add the note in array list  and search to all column to find the note you enter it
           do {
               Not1.add(c.getString(c.getColumnIndex("Note")))
               // will search to the next column
           }while (c.moveToNext())
       }
        //will return the Not1
        return Not1

    }


}
