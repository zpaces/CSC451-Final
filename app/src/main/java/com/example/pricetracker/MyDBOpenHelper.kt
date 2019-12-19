package com.example.pricetracker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBOpenHelper(
    context: Context?,
    //name: String?,
    factory: SQLiteDatabase.CursorFactory?
    //version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
//Setup DB
    companion object{
        private val version = 1
        private val name = "product.db"
        val TABLE_NAME = "products"
        val COLUMN_ID = "_id"
        val COLUMN_NAME1 = "amazonURL"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME1 + " TEXT," + ")")

        db?.execSQL(CREATE_PRODUCTS_TABLE)
    }


    override fun onUpgrade(db:  SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //insert new row
    fun addName(name: Product){
        val values = ContentValues()
        values.put(COLUMN_NAME1, name.amazonURL)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null ,values)
        db.close()
    }
    //The search to get all stored values
    fun getAllName(): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }
}