package com.example.notesapp

import android.content.ContentValues
import android.content.Context
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

}
