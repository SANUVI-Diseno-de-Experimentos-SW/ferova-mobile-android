package com.example.pe.edu.upc.ferova_mobile_android

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pe.edu.upc.ferova_mobile_android.presentation.navigation.NavGraph
import org.osmdroid.config.Configuration
import pe.edu.upc.ferovafamily.presentation.theme.FerovaFamilyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val ctx = applicationContext
        Configuration.getInstance().apply {
            load(ctx, ctx.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
            userAgentValue = "FerovaFamily/1.0 (tucorreo@upc.edu.pe)"
            osmdroidBasePath = java.io.File(ctx.filesDir, "osmdroid")
            osmdroidTileCache = java.io.File(ctx.filesDir, "osmdroid/tiles")
        }

        enableEdgeToEdge()
        setContent {
            FerovaFamilyTheme {
                NavGraph()
            }
        }
    }
}