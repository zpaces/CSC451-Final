package com.example.pricetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var urlButton: Button
    lateinit var productText: EditText
    lateinit var viewProductsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productText = findViewById(R.id.productText)
        urlButton = findViewById(R.id.urlButton)
        viewProductsButton = findViewById(R.id.veiwProductsButton)

        urlButton.setOnClickListener{
            val dbHandler = MyDBOpenHelper(this,null)


            val user = Product(productText.text.toString(), productText.text.toString())

            dbHandler.addName(user)

            Toast.makeText(this, productText.text.toString(), Toast.LENGTH_LONG)
                .show()

            productText.setText ("")
        }
        viewProductsButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, my_list::class.java)

            startActivity(intent)
        }
    }
}
