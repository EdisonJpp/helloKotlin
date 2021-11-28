package com.example.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts : TextToSpeech? = null ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this);
        findViewById<Button>(R.id.btnPlay).setOnClickListener { this.speak() }
    }

    private fun speak (){
        var message: String  = findViewById<TextView>(R.id.etMessage).text.toString();
        if(message.isEmpty()){
           findViewById<TextView>(R.id.tvStatus).text = "Must to write a message";
           message = "is seriously? , You must write a message";
        }

        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "");
    }

    override fun onInit(status: Int) {
        if(status === TextToSpeech.SUCCESS){
            tts!!.setLanguage(Locale.US);
        }  else{
            findViewById<TextView>(R.id.tvStatus).text = "available";
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop();
            tts!!.shutdown();
        }
        super.onDestroy();
    }
}