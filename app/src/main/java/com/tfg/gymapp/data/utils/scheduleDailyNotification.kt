package com.tfg.gymapp.data.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.*

fun scheduleDailyNotification(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, DailyNotificationReceiver::class.java)

    // Verificar si ya existe un PendingIntent con este intent
    val existingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
    )

    // Si ya está programado, salir
    if (existingIntent != null) {
        Log.i("Notificaciones", "⏱ Ya existe una notificación programada, no se vuelve a crear.")
        return
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 11)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)

        // Si la hora ya pasó hoy, programa para mañana
        if (before(Calendar.getInstance())) {
            add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    val triggerTime = calendar.timeInMillis

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (alarmManager.canScheduleExactAlarms()) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Log.i("Notificaciones", "✅ Notificación diaria programada para las 11:00 (exacta)")
        } else {
            Log.w("Notificaciones", "❌ No se puede programar alarmas exactas en Android 12+ sin permiso")
        }
    } else {
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
        Log.i("Notificaciones", "✅ Notificación diaria programada para las 11:00")
    }
}
