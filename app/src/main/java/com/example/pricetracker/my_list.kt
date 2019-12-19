package com.example.pricetracker

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView


class my_list : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_list)
        //Start the interaction with the DB
        val dbHandler = MyDBOpenHelper(this,null)
        //Get the data from the DB
        val cursor = dbHandler.getAllName()

        cursor!!.moveToFirst()
        //put the DB Data into an array
        var listTest = ArrayList<String>()

        listTest.add(cursor.getString(cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)))

        while(cursor.moveToNext())
            listTest.add(cursor.getString(cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)))

        val listView = findViewById<ListView>(R.id.mainListView)

        listView.adapter = listAdapter(this)

        //Search the selected array
        listView.setOnItemClickListener { parent, view, position, id ->
            var theURL = listTest[position]
            var webaddress = Uri.parse(theURL)

            val intent = Intent(Intent.ACTION_VIEW, webaddress)
            startActivity(intent)
        }

    }
    class listAdapter(context: Context): BaseAdapter(){

        private val mContext: Context

        init{
            mContext = context
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main, viewGroup, false)

            val dbHandler = MyDBOpenHelper(mContext,null)

            val cursor = dbHandler.getAllName()

            cursor!!.moveToFirst()

            var listTest = ArrayList<String>()

            listTest.add(cursor.getString(cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)))

            while(cursor.moveToNext())
                listTest.add(cursor.getString(cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)))

            val positionTextView = rowMain.findViewById<TextView>(R.id.listVal)
            positionTextView.text = listTest[position]

            return rowMain
            //   val textView = TextView(mContext)
         //   textView.text = "Here Is my Row for my List"
         //   return textView
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            val dbHandler = MyDBOpenHelper(mContext,null)

            val cursor = dbHandler.getAllName()

            cursor!!.moveToFirst()

            var listTest = ArrayList<String>()

            listTest.add(cursor.getString(cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)))

            while(cursor.moveToNext())
                listTest.add(cursor.getString(cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)))

            return listTest.size
        }

    }
}
