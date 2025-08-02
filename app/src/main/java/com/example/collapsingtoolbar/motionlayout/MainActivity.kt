package com.example.collapsingtoolbar.motionlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.collapsingtoolbar.motionlayout.ui.ToolBarExampleDsl
import com.example.collapsingtoolbar.motionlayout.ui.theme.CollapsingToolbarMotionLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollapsingToolbarMotionLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToolBarExampleDsl(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}