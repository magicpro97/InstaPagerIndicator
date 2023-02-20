package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.CCLineChart
import com.example.myapplication.ui.Point
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CCLineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        data = listOf(
                            Point(10f, 7f, image = "https://picsum.photos/100/100"),
                            Point(20f, 10f),
                            Point(30f, 20f),
                            Point(40f, 14f, image = "https://picsum.photos/100/100"),
                            Point(50f, 24f),
                            Point(60f, 17f),
                            Point(70f, 10f, image = "https://picsum.photos/100/100"),
                            Point(80f, 13f),
                            Point(90f, 7f, image = "https://picsum.photos/100/100"),
                            Point(100f, 3f),
                        ),
                        itemCountX = 4,
                        itemCountY = 5,
                    )
                }
            }
        }
    }
}
