package com.example.selfaccesment

import android.app.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.WorkManager
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.app.AlarmManager
import android.app.PendingIntent
import android.util.Log
import android.util.Log.*
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import java.util.concurrent.TimeUnit
import androidx.work.OneTimeWorkRequestBuilder as WorkOneTimeWorkRequestBuilder


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    lateinit var alarmManager: AlarmManager
    private val channeId = "com.example.selfaccesment"
    private val description = "Test notificiation"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val yes = findViewById(R.id.yes) as Button
        val no = findViewById(R.id.no) as Button
        val time = SimpleDateFormat("hh:mm:ss")
        val currentTime = time.format(Date())
        val request = OneTimeWorkRequest.Builder(MyWork::class.java).setInitialDelay(10, TimeUnit.SECONDS).build()

        if (4 > 3) {

            WorkManager.getInstance(this).enqueue(request)
        }
//        notify.setOnClickListener {
//
//            val intent = Intent(this,LauncherActivity::class.java)
//            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
//            val alarmpendingintent = PendingIntent.getBroadcast(this, 0, intent, 0)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//                notificationChannel = NotificationChannel(channeId,description,NotificationManager.IMPORTANCE_HIGH)
//                notificationChannel.enableLights(true)
//                notificationChannel.lightColor = Color.GREEN
//                notificationChannel.enableVibration(false)
//                notificationManager.createNotificationChannel(notificationChannel)
//
//                builder = Notification.Builder(this,channeId)
//                    .setContentTitle("CodeAndroid")
//                    .setContentText("Test Notification")
//                    .setSmallIcon(R.mipmap.ic_launcher_round)
//                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
//                    .setContentIntent(pendingIntent)
//            }else{
//                builder = Notification.Builder(this)
//                    .setContentTitle("CodeAndroid")
//                    .setContentText("Test Notification")
//                    .setSmallIcon(R.mipmap.ic_launcher_round)
//                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
//                    .setContentIntent(pendingIntent)
//            }
//            notificationManager.notify(1234,builder.build())
//            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 5000,alarmpendingintent)
//        }
        yes.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var file = FileWriter(
                File(applicationContext.getExternalFilesDir(null), "responses.txt"),
                true
            )
            if (File(applicationContext.getExternalFilesDir(null), "responses.txt").exists()) {
                file.write("Cough,$currentDate, Yes\n")
                file.close()
            } else {
                File(
                    applicationContext.getExternalFilesDir(null),
                    "responses.txt"
                ).createNewFile()

                File(
                    applicationContext.getExternalFilesDir(null),
                    "responses.txt"
                ).writeText("Cough,$currentDate,Yes")
            }
        }
        no.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var file = FileWriter(
                File(applicationContext.getExternalFilesDir(null), "responses.txt"),
                true
            )
            if (File(applicationContext.getExternalFilesDir(null), "responses.txt").exists()) {
                file.write("Cough,$currentDate, No\n")
                file.close()
            } else {
                File(
                    applicationContext.getExternalFilesDir(null),
                    "responses.txt"
                ).createNewFile()

                File(
                    applicationContext.getExternalFilesDir(null),
                    "responses.txt"
                ).writeText("Cough,$currentDate,No")
            }
        }
    }
}

