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

        val value1 = intent.getIntExtra("EXTRA_VALUE_1", 0)
        val value2 = intent.getIntExtra("EXTRA_VALUE_2", 0)
        val value3 = intent.getIntExtra("EXTRA_VALUE_3", 0)
        val value4 = intent.getIntExtra("EXTRA_VALUE_4", 0)

        findViewById<TextView>(R.id.txtValue1).text = value1.toString()
        findViewById<TextView>(R.id.txtValue2).text = value2.toString()
        findViewById<TextView>(R.id.txtValue3).text = value3.toString()
        findViewById<TextView>(R.id.txtValue4).text = value4.toString()

        findViewById<Button>(R.id.btnSum).setOnClickListener {
            val sum = value1 + value2 + value3 + value4
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_RESULT", sum)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.btnProduct).setOnClickListener {
            val product = value1 * value2 * value3 * value4
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_RESULT", product)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}