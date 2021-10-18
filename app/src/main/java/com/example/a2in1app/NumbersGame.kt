package com.example.a2in1app

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class NumbersGame : AppCompatActivity() {
    //declare
    lateinit var rvGuesses: RecyclerView
    lateinit var input: EditText
    lateinit var enterGuess: Button

    lateinit var guesses: ArrayList<String>

    //var rotateGuesses:String = "0"
    private var answer = 0
    var trying = 3
    var theInput = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_game)
        //initialize
        rvGuesses = findViewById(R.id.rvGuesses)
        input = findViewById(R.id.numberInput)
        enterGuess = findViewById(R.id.button2)

        guesses = ArrayList()
        //rotateGuesses = "${guesses.toString()}"
        answer = Random.nextInt(1, 11)

        //Recycler View
        rvGuesses.adapter = rvAllGuesses(guesses)
        rvGuesses.layoutManager = LinearLayoutManager(this)
        //utilize
        enterGuess.setOnClickListener {
            theInput = input.text.toString()
            if (theInput.isNotEmpty()) {
                if (trying >= 1) {
                    if (theInput.toInt() == answer) {
                        guesses.add("You guessed $theInput")
                        guesses.add("You guessed it right!")
                        customAlert()
                    } else if (theInput.toInt() != answer) {
                        trying--
                        if (trying > 1) {
                            guesses.add("You guessed $theInput")
                            guesses.add("You have $trying guesses left!")
                        } else if (trying == 1) {
                            guesses.add("You guessed $theInput")
                            guesses.add("Your last chance!")
                        }
                    }
                } else if (trying == 0) {
                    guesses.add("You guessed $theInput")
                    guesses.add("Your guesses are finished! The answer was $answer")
                    customAlert()
                }
            }
            rvGuesses.adapter?.notifyDataSetChanged()
            input.text.clear()
            input.clearFocus()
        }
    }

    private fun customAlert() {

        val builder1 = AlertDialog.Builder(this)

        // set message of alert dialog
        builder1.setMessage("Do you want to play again?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        val wish = builder1.create()
        wish.setTitle("New Game")
        wish.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("theGame", guesses)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        guesses = savedInstanceState.getStringArrayList("theGame") as ArrayList<String>
        rvGuesses.adapter?.notifyDataSetChanged()

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