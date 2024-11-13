package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import java.util.*

class PracticalTest01Var07Service : Service() {

    private val handler = Handler()
    private lateinit var runnable: Runnable

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        runnable = object : Runnable {
            override fun run() {
                val random = Random()

                // Generăm 4 valori aleatorii între 1 și 100
                val value1 = random.nextInt(100) + 1
                val value2 = random.nextInt(100) + 1
                val value3 = random.nextInt(100) + 1
                val value4 = random.nextInt(100) + 1

                // Creăm un intent pentru mesajul de difuzare
                val broadcastIntent = Intent("com.example.yourapp.UPDATE_VALUES")
                broadcastIntent.putExtra("value1", value1)
                broadcastIntent.putExtra("value2", value2)
                broadcastIntent.putExtra("value3", value3)
                broadcastIntent.putExtra("value4", value4)

                // Difuzăm mesajul
                sendBroadcast(broadcastIntent)

                // Reprogramăm task-ul să se execute din nou după 10 secunde
                handler.postDelayed(this, 10000)
            }
        }

        // Începem difuzarea mesajului
        handler.post(runnable)

        // Dacă serviciul este oprit, să-l repornească automat
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Oprim task-ul atunci când serviciul este distrus
        handler.removeCallbacks(runnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // Nu oferim un binding pentru acest serviciu
    }
}
