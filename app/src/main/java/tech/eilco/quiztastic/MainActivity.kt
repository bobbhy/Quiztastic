package tech.eilco.quiztastic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://the-trivia-api.com/api/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_next:Button=findViewById(R.id.btn_nextActivity)
        button_next.setOnClickListener{
            val intent=Intent(this,CategorySelectActivity::class.java)
            startActivity(intent)
        }
    }

}