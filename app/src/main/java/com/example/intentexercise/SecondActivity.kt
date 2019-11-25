package com.example.intentexercise

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(MainActivity.KEY)
        val number = intent.getIntExtra(MainActivity.KEY2,0)
        textViewMessage.text = String.format("%s %s", getString(R.string.message),message)
        textViewNumber.text = String.format("%s %d", getString(R.string.lucky_number), number)

        buttonDone.setOnClickListener{
            if(editTextReply.text.isNotEmpty()){
                val reply = editTextReply.text.toString()
                val intent = getIntent() //return the MainActivity intent
                intent.putExtra(MainActivity.REPLY,reply)

                //Inform the MainActivity that everything is OK
                setResult(Activity.RESULT_OK, intent)
            }else{
                setResult(Activity.RESULT_CANCELED)
            }


            finish()
        }
    }
}
