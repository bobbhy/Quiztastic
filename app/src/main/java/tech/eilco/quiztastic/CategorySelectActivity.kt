package tech.eilco.quiztastic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import tech.eilco.quiztastic.databinding.ActivityCategorySelectBinding

class CategorySelectActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCategorySelectBinding
    private var category:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCategorySelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        this.title="Choose a categorie"
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when (v) {
            binding.btn1->{
                this.category="general_knowledge"
            }
            binding.btn2->{
                this.category="arts_and_Literature"
            }
            binding.btn3->{
                this.category="film_and_TV"
            }
            binding.btn4->{
                this.category="geography"
            }
            binding.btn5->{
                this.category="history"
            }
            binding.btn6->{
                this.category="music"
            }
            binding.btn7->{
                this.category="science"
            }
            binding.btn8->{
                this.category="society_and_culture"
            }
            binding.btn9->{
                this.category="sport_and_leisure"
            }
        }
        Log.i("####",this.category)
        val intent= Intent(this,QuizQuestionsActivity::class.java)
        intent.putExtra("category",this.category)
        startActivity(intent)

    }
}