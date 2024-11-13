package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07MainActivity : AppCompatActivity() {


    private var sumResult: Int = 0
    private var productResult: Int = 1

    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        val btnSet: Button = findViewById(R.id.btnSet)

        // Pornim serviciul atunci când activitatea este creată
        val serviceIntent = Intent(this, PracticalTest01Var07Service::class.java)
        startService(serviceIntent)

        // Definim un receiver pentru a asculta mesajele de difuzare
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val value1 = intent.getIntExtra("value1", 0)
                val value2 = intent.getIntExtra("value2", 0)
                val value3 = intent.getIntExtra("value3", 0)
                val value4 = intent.getIntExtra("value4", 0)

                // Suprascriem câmpurile text cu valorile primite
                findViewById<EditText>(R.id.editText1).setText(value1.toString())
                findViewById<EditText>(R.id.editText2).setText(value2.toString())
                findViewById<EditText>(R.id.editText3).setText(value3.toString())
                findViewById<EditText>(R.id.editText4).setText(value4.toString())

                // Afișăm un Toast ca feedback
                Toast.makeText(this@PracticalTest01Var07MainActivity, "Values Updated", Toast.LENGTH_SHORT).show()
            }
        }

        // Înregistrăm receiver-ul pentru a asculta mesajele de difuzare
        val filter = IntentFilter("com.example.yourapp.UPDATE_VALUES")
        registerReceiver(receiver, filter)

        // Setăm ascultătorul pentru butonul "Set"
        btnSet.setOnClickListener {
            // Extragem valorile și ignorăm câmpurile goale
            val values = listOf(
                findViewById<EditText>(R.id.editText1).text.toString().toIntOrNull(),
                findViewById<EditText>(R.id.editText2).text.toString().toIntOrNull(),
                findViewById<EditText>(R.id.editText3).text.toString().toIntOrNull(),
                findViewById<EditText>(R.id.editText4).text.toString().toIntOrNull()
            )

            // Verificăm dacă cel puțin unul dintre câmpuri conține un număr
            if (values.any { it != null }) {
                // Creăm intentul pentru a porni SecondaryActivity
                val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)
                values.forEachIndexed { index, value ->
                    value?.let { intent.putExtra("EXTRA_VALUE_$index", it) }
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

    override fun onDestroy() {
        super.onDestroy()

        // Oprim serviciul atunci când activitatea este distrusă
        stopService(Intent(this, PracticalTest01Var07Service::class.java))

        // Anulăm înregistrarea receiver-ului
        unregisterReceiver(receiver)
    }
}