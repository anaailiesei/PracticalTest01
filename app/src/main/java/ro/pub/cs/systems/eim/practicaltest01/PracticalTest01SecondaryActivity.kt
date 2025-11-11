package ro.pub.cs.systems.eim.practicaltest01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_secondary)

        val receivedData = intent.getStringExtra("some_key")
        (findViewById(R.id.textview) as TextView).setText("Buttons pressed $receivedData times")

        // Presupunând că vrei să returnezi rezultatul la un anumit eveniment, de exemplu, la apăsarea unui buton
        val someButton: Button = findViewById(R.id.ok)

        someButton.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra("another_key", "anotherValue")
            }
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        val button: Button = findViewById(R.id.cancel)
        button.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra("another_key", "anotherValue")
            }
            setResult(RESULT_CANCELED, returnIntent)
            finish()
        }

        // Dacă activitatea se încheie fără a seta explicit un rezultat, poți să nu faci nimic sau să setezi RESULT_CANCELED
    }
}