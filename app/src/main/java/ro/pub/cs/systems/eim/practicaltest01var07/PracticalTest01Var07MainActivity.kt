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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        val btnSet: Button = findViewById(R.id.btnSet)

        btnSet.setOnClickListener {
            val editText1 = findViewById<EditText>(R.id.editText1).text.toString().toIntOrNull() ?: 0
            val editText2 = findViewById<EditText>(R.id.editText2).text.toString().toIntOrNull() ?: 0
            val editText3 = findViewById<EditText>(R.id.editText3).text.toString().toIntOrNull() ?: 0
            val editText4 = findViewById<EditText>(R.id.editText4).text.toString().toIntOrNull() ?: 0

            // Create intent to open PracticalTest01Var07SecondaryActivity
            val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)
            intent.putExtra("EXTRA_VALUE_1", editText1)
            intent.putExtra("EXTRA_VALUE_2", editText2)
            intent.putExtra("EXTRA_VALUE_3", editText3)
            intent.putExtra("EXTRA_VALUE_4", editText4)
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val result = data?.getIntExtra("EXTRA_RESULT", 0) ?: 0
            Toast.makeText(this, "Result: $result", Toast.LENGTH_LONG).show()
            Log.d("MainActivity", "Result: $result")
        }
    }

}