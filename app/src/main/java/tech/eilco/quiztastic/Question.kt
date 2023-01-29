package tech.eilco.quiztastic

data class Question(
    val category: String,
    val correctAnswer: String,
    val difficulty: String,
    val id: String,
    val incorrectAnswers: List<String>,
    val isNiche: Boolean,
    val question: String,
    val regions: List<Any>,
    val tags: List<String>,
    val type: String,
    var answers:MutableList<String>?
)