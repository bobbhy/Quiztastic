package tech.eilco.quiztastic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        Log.i("aralia", "onCreate: $score")

        val congratulationsTv: TextView = findViewById(R.id.congratulationsTv)
        val scoreTv: TextView = findViewById(R.id.scoreTv)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        congratulationsTv.text = "Congratulations!"
        scoreTv.text = "Your score is $score / 10"
        btnRestart.setOnClickListener {
            val intent = Intent(this, CategorySelectActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
