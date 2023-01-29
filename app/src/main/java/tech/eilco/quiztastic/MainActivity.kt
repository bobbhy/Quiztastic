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
        getData()
    }
    private fun getData(){
        val textView:TextView=findViewById(R.id.textMessage)
        val retrofitBuilder=Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(ApiInterface::class.java)
        val retrofitData=retrofitBuilder.getData(10,null)
        retrofitData.enqueue(object : Callback<List<Question>?> {
            override fun onResponse(
                call: Call<List<Question>?>,
                response: Response<List<Question>?>
            ) {
                val responseBody=response.body()!!
                val myStringBuilder=StringBuilder()
                var i=1;
                for(myData in responseBody)
                {
                    myStringBuilder.append("Question "+i+" "+myData.question)
                    myStringBuilder.append("\n")
                    i += 1
                }
//                textView.text=myStringBuilder
            }

            override fun onFailure(call: Call<List<Question>?>, t: Throwable) {
                Log.d("MainActivity","Message :"+t.message)
            }
        })
    }

}