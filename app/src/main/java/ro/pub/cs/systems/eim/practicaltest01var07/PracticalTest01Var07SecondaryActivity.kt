package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)


        // Extragem valorile din Intent și le plasăm în TextViews
        val values = listOf(
            intent.getIntExtra("EXTRA_VALUE_0", Int.MIN_VALUE),
            intent.getIntExtra("EXTRA_VALUE_1", Int.MIN_VALUE),
            intent.getIntExtra("EXTRA_VALUE_2", Int.MIN_VALUE),
            intent.getIntExtra("EXTRA_VALUE_3", Int.MIN_VALUE)
        )

        // Populăm câmpurile TextView corespunzătoare
        val textFields = listOf(
            findViewById<TextView>(R.id.txtValue1),
            findViewById<TextView>(R.id.txtValue2),
            findViewById<TextView>(R.id.txtValue3),
            findViewById<TextView>(R.id.txtValue4)
        )

        values.forEachIndexed { index, value ->
            if (value != Int.MIN_VALUE) {
                textFields[index].text = value.toString()
            } else {
                textFields[index].text = ""
            }
        }

        // Calculăm suma și produsul ignorând câmpurile goale
        findViewById<Button>(R.id.btnSum).setOnClickListener {
            val sum = values.filter { it != Int.MIN_VALUE }.sum()
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_RESULT", sum)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.btnProduct).setOnClickListener {
            val product = values.filter { it != Int.MIN_VALUE }.fold(1) { acc, i -> acc * i }
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_RESULT", product)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}