package com.example.intentexercise

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener{
            sendMessage()
        }
        buttonCall.setOnClickListener{
            dialPhone()
        }
        buttonWeb.setOnClickListener {
            visitWeb()
        }
    }

    private fun visitWeb(){
        val intent = Intent(Intent.ACTION_VIEW)
        val web: String = "tel:0123456789"

        //Check package manager for app to handle an intent
        intent.setData(Uri.parse(web))
        if(intent.resolveActivity(packageManager) !== null){
            startActivity(intent)
        }
    }

    private fun dialPhone(){
        val intent = Intent(Intent.ACTION_VIEW)
        val phone: String = "tel:0123456789"

        //Check package manager for app to handle an intent
        intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager) !== null){
            startActivity(intent)
        }
    }

    private fun sendMessage(){
        //Explicit intent
        val intent = Intent(this,SecondActivity::class.java)
        val message = editTextMessage.text.toString()
        val number = editTextNumber.text.toString().toInt()
        intent.putExtra(KEY, message)
        intent.putExtra(KEY2, number)

        //startActivity(intent) //Intent without return value
        startActivityForResult(intent, REQUEST_CODE)  //Intent with return
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(REPLY).toString()
                textViewReply.text = String.format("%s %s", getString(R.string.reply),reply)
            }
        }
    }

    companion object{
        //unique constant
        const val KEY = "com.example.intentexercise.KEY"
        const val KEY2 = "com.example.intentexercise.KEY2"
        const val REPLY = "com.example.intentexercise.REPLY"
        const val REQUEST_CODE = 1
    }
}
