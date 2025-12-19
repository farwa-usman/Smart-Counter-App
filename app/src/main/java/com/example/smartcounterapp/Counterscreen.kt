package com.example.smartcounterapp

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun CounterScreen(){
    var counter by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    { Text("$counter", fontSize = 20.sp)
    Spacer(modifier = Modifier.height(16.dp))
   Row{
       // Increment Button
       Button(onClick = {counter++}, enabled = counter<20,
        colors = ButtonDefaults.
        buttonColors(containerColor = Color.Green))
    {Text("Increase", fontSize = 18.sp)}
       Spacer(modifier = Modifier.width(16.dp))
       // Decrement Button
    Button(onClick = {if (counter>0) counter--},
        colors = ButtonDefaults.
        buttonColors(containerColor = Color.Red))
    {Text("Decrease", fontSize = 18.sp)}}}}


