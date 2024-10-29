package com.syrok.AndroidHseLab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.syrok.AndroidHseLab1.ui.theme.AndroidHseLab1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidHseLab1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var greeting by rememberSaveable { mutableStateOf("") }
                        var fullGreeting by rememberSaveable { mutableStateOf("") }
                        val activityLauncher =
                            rememberLauncherForActivityResult(NameActivityContract()) { result ->
                                if (result != null) {
                                    fullGreeting = result
                                }
                            }

                        TextField(
                            label = { Text("Введите приветствие:") },
                            value = greeting,
                            maxLines = 2,
                            onValueChange = {
                                if (it.length <= 30) greeting = it
                            }
                        )

                        Button(onClick = {
                            activityLauncher.launch(greeting)
                        }, enabled = greeting.isNotEmpty()) {
                            Text("К вводу имени")
                        }

                        if (fullGreeting.isNotEmpty()) {
                            Text(
                                text = fullGreeting,
                                fontSize = 48.sp,
                                lineHeight = 48.sp
                            )
                        }
                    }
                }
            }
        }
    }
}