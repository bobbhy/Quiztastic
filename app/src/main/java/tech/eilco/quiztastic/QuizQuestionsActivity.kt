package tech.eilco.quiztastic

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.eilco.quiztastic.databinding.ActivityQuizQuestionsBinding


class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mSelectedOptionValue: String = ""
    private var score = 0
    private var category: String? = null
    private var disabled: Boolean = false
    private lateinit var binding: ActivityQuizQuestionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        this.category = intent.getStringExtra("category").toString()
        this.title = "Question" + (mCurrentPosition)
        getData(this.category)
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.tvOptionOne -> {
                if (!this.disabled)
                    selectedOptionView(binding.tvOptionOne, 1)
            }
            binding.tvOptionTwo -> {
                if (!this.disabled)
                    selectedOptionView(binding.tvOptionTwo, 2)
            }
            binding.tvOptionThree -> {
                if (!this.disabled)
                    selectedOptionView(binding.tvOptionThree, 3)
            }
            binding.tvOptionFour -> {
                if (!this.disabled)
                    selectedOptionView(binding.tvOptionFour, 4)
            }
            binding.btnSubmit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    this.disabled = false
                    this.title = "Question" + (mCurrentPosition)
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this@QuizQuestionsActivity, Score::class.java)
                            intent.putExtra("score", score)
                            startActivity(intent)
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question?.correctAnswer != mSelectedOptionValue) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        score--
                    }
                    score++
                    answerView(
                        question?.answers!!.indexOf(question.correctAnswer) + 1,
                        R.drawable.correct_option_border_bg
                    )
                    if (mCurrentPosition == mQuestionList!!.size) {
                        binding.btnSubmit.text = "Finish"
                    } else {
                        binding.btnSubmit.text = "Go to next question"
                    }
                    this.disabled = true
                    mSelectedOptionPosition = 0
                    mSelectedOptionValue = ""
                }

            }
        }
    }

    private fun getData(category: String?) {
        val retrofitBuilder =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
                .build().create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData(10, category)
        retrofitData.enqueue(object : Callback<List<Question>?> {
            override fun onResponse(
                call: Call<List<Question>?>,
                response: Response<List<Question>?>
            ) {
                val responseBody = response.body()!!
                for (myData in responseBody) {
                    val tempAnswers = myData.incorrectAnswers!!.toMutableList()
                    tempAnswers.add(myData.correctAnswer)
                    tempAnswers.shuffle()
                    myData.answers = tempAnswers!!
                }
                mQuestionList = ArrayList(responseBody)
                setQuestion()
            }

            override fun onFailure(call: Call<List<Question>?>, t: Throwable) {
                Log.d("MainActivity", "Message :" + t.message)
            }
        })
    }

    private fun setQuestion() {
        val question: Question = this.mQuestionList!!.get(mCurrentPosition - 1)
        defaultOptionsView()
        binding.btnSubmit.text = "Submit"
        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" + binding.progressBar.max

        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.answers!![0]
        binding.tvOptionTwo.text = question.answers!![1]
        binding.tvOptionThree.text = question.answers!![2]
        binding.tvOptionFour.text = question.answers!![3]

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        mSelectedOptionValue = tv.text.toString()
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }
}