package com.example.a2in1app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var buttonStartNum: Button
    lateinit var buttonStartPhrase: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStartNum = findViewById(R.id.buttonStartNum)
        buttonStartPhrase = findViewById(R.id.buttonStartPhrase)

        buttonStartNum.setOnClickListener{
            val intent = Intent(this, NumbersGame::class.java)
            startActivity(intent)
        }
        buttonStartPhrase.setOnClickListener{
            val intent = Intent(this, GuessThePhrase::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.itemHome -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.itemNumbers -> {
                val intent = Intent(this, NumbersGame::class.java)
                startActivity(intent)
                return true
            }
            R.id.itemPhrase -> {
                val intent = Intent(this, GuessThePhrase::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}