package ro.pub.cs.systems.eim.practicaltest01var07
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    private var sumResult: Int = 0
    private var productResult: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        val btnSet: Button = findViewById(R.id.btnSet)

        btnSet.setOnClickListener {
            // Extragem valorile și ignorăm câmpurile goale
            val values = listOf(
                findViewById<EditText>(R.id.editText1).text.toString().toIntOrNull(),
                findViewById<EditText>(R.id.editText2).text.toString().toIntOrNull(),
                findViewById<EditText>(R.id.editText3).text.toString().toIntOrNull(),
                findViewById<EditText>(R.id.editText4).text.toString().toIntOrNull()
            )

            if (values.any { it != null }) {
                // Creăm intentul și adăugăm doar valorile care nu sunt null
                val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)
                values.forEachIndexed { index, value ->
                    intent.putExtra("EXTRA_VALUE_$index", value)
                }
                startActivityForResult(intent, 1)
            } else {
                // Dacă toate câmpurile sunt goale, ignorăm acțiunea
                Toast.makeText(this, "Please enter at least one number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val result = data?.getIntExtra("EXTRA_RESULT", 0) ?: 0

            // Actualizăm suma și produsul în variabilele locale
            if (result > 0) {
                sumResult = result
            } else {
                productResult = result
            }

            // Afișăm rezultatul într-un Toast
            Toast.makeText(this, "Result: $result", Toast.LENGTH_LONG).show()
            Log.d("MainActivity", "Result: $result")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SUM_RESULT", sumResult)
        outState.putInt("PRODUCT_RESULT", productResult)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        sumResult = savedInstanceState.getInt("SUM_RESULT", 0)
        productResult = savedInstanceState.getInt("PRODUCT_RESULT", 1)

        // Afișăm rezultatele restaurate într-un Toast
        Toast.makeText(this, "Restored Sum: $sumResult, Product: $productResult", Toast.LENGTH_LONG).show()
    }

}