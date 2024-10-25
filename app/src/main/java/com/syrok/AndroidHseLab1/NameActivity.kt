package com.syrok.AndroidHseLab1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
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

class NameActivity : ComponentActivity() {
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
                        val greeting = intent.getStringExtra("greeting")
                        var name by rememberSaveable { mutableStateOf("") }

                        Text(greeting.toString(), fontSize = 32.sp)
                        TextField(
                            label = { Text("Введите ваше имя:") },
                            value = name,
                            onValueChange = { newText -> name = newText }
                        )

                        Button(onClick = {
                            setResult(RESULT_OK, Intent().putExtra("name", "$greeting, $name!"))
                            finish()
                        }) {
                            Text("Обратно к приветствию")
                        }
                    }
                }
            }
        }
    }
}