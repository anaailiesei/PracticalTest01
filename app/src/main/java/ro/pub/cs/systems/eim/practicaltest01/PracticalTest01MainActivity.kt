package ro.pub.cs.systems.eim.practicaltest01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01MainActivity : AppCompatActivity() {
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private inner class Listener : View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                R.id.button_1 -> {
                    val view1 = findViewById(R.id.edit_1) as TextView
                    val text = view1.text
                    val num = text.
                            toString().toInt()
                    Log.i("APLICATIE", "$num")
                    view1.setText((num + 1).toString())
                }
                R.id.button_2 -> {
                    val view2 = findViewById(R.id.edit_2) as TextView
                    val text = view2.text
                    val num = text.
                    toString().toInt()
                    view2.setText((num + 1).toString())
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        (findViewById(R.id.button_1) as Button).setOnClickListener(Listener())
        (findViewById(R.id.button_2) as Button).setOnClickListener(Listener())

        val edit1 = findViewById(R.id.edit_1) as EditText
        val edit2 = findViewById(R.id.edit_2) as EditText
        if (savedInstanceState != null) {


            if (savedInstanceState.containsKey(Constants.EDIT1_TEXT)) {
                edit1.setText(savedInstanceState.getString(Constants.EDIT1_TEXT))
            }
            if (savedInstanceState.containsKey(Constants.EDIT1_TEXT)) {
                edit2.setText(savedInstanceState.getString(Constants.EDIT2_TEXT))
            }
        }

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Procesează rezultatul aici
                Toast.makeText(this, "Rezultat ok", Toast.LENGTH_SHORT).show()
                Log.i(Constants.TAG, "Rezultat ok")
            }  else if (result.resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Rezultat cancel", Toast.LENGTH_SHORT).show()
                Log.i(Constants.TAG, "Rezultat cancel")
            }
        }

        val btn = findViewById<Button>(R.id.button_intent)
        btn.setOnClickListener {
            val val1 = edit1.text.toString().toInt()
            val val2 = edit2.text.toString().toInt()
            val intent = Intent(this, PracticalTest01SecondaryActivity::class.java).apply {
                putExtra("some_key", (val1 + val2).toString())
            }
            // Lansează activitatea copil folosind launcher-ul
            startForResult.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val edit1 = findViewById(R.id.edit_1) as EditText
        val edit2 = findViewById(R.id.edit_2) as EditText

            outState.putString(Constants.EDIT1_TEXT, edit1.text.toString())
//            outState.putString(Constants.PASSWORD_EDIT_TEXT, passwordEditText.text.toString())
            outState.putString(Constants.EDIT2_TEXT, edit2.text.toString())
    }
}