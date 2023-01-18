package tech.eilco.quiztastic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CategorySelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_select)
        this.title="Choose a categorie"
    }
}