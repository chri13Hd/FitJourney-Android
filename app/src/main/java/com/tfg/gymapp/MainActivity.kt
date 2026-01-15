package com.tfg.gymapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tfg.gymapp.navigation.NavigationGraph
import com.tfg.gymapp.ui.theme.GymAppTheme
import com.tfg.gymapp.data.utils.scheduleDailyNotification
import androidx.core.content.edit

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Solicitar permisos si es Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }

        // Verificar si ya se programó la notificación
        val sharedPrefs = getSharedPreferences("notification_prefs", MODE_PRIVATE)
        val isScheduled = sharedPrefs.getBoolean("notification_scheduled", false)

        if (!isScheduled) {
            scheduleDailyNotification(this)
            sharedPrefs.edit() { putBoolean("notification_scheduled", true) }
        }

        setContent {
            GymAppTheme {
                NavigationGraph()
            }
        }
    }
}
