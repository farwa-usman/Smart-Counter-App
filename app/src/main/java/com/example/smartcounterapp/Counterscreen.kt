package com.example.smartcounterapp

import android.R
import android.R.attr.content
import android.R.attr.padding
import android.inputmethodservice.Keyboard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.text.substringAfter
data class Record(
    val id:Long,
   val action:String,
   val value:Int)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun CounterScreen(){
    val snackbarHostState=remember { SnackbarHostState()}
    var counter by remember{ mutableStateOf(0) }
    var scope = rememberCoroutineScope()
    var record=remember { mutableStateListOf<Record>() }
    Scaffold (
        topBar = { TopAppBar(title = {Text("Smart Counter",
             fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif)},
            colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red, titleContentColor = Color.White))},
      snackbarHost={SnackbarHost(hostState =snackbarHostState )},
       content =  {  PaddingValues->
          Column(modifier = Modifier.padding(PaddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        { Text("$counter", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row{
                // Increment Button
                Button(onClick = {counter++
                    record.add(Record(id= System.currentTimeMillis(),action="Increase", value=counter))
                    if(counter==20) scope.launch {val result= snackbarHostState
                        .showSnackbar("Maximum Limit reached", actionLabel = "Retry")
                    if (result== SnackbarResult.ActionPerformed)
                    {counter=0
                        record.add(Record(id= System.currentTimeMillis(),action="Reset", value=counter))
                    }
                    }
                                 }, enabled = counter<20,
                    colors = ButtonDefaults.
                    buttonColors(containerColor = Color.Green))
                {Text("Increase", fontSize = 18.sp)}
                Spacer(modifier = Modifier.width(16.dp))
                // Decrement Button
                Button(onClick =
                    {if (counter==0) scope.launch {
                        snackbarHostState.showSnackbar("Already zero") }  else counter--
                  if (counter>0)  record.add(Record(id= System.currentTimeMillis(),action="Decrease", value=counter))
                    },
                    colors = ButtonDefaults.
                    buttonColors(containerColor = Color.Red))
                {Text("Decrease", fontSize = 18.sp)}
                }
            Spacer(modifier = Modifier.height(25.dp))
           // Divider(thickness = 5.dp)
            Spacer(modifier = Modifier.height(40.dp))
            // object Creation
             if (record.isEmpty()) Text("No actions yet", fontWeight = FontWeight.SemiBold, color = Color.DarkGray

             )
             else
            { LazyColumn(contentPadding = PaddingValues(vertical = 3.dp)){
               item {
                   Divider(thickness = 3.dp)
                  Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
                      Text("Record", modifier = Modifier.padding(10.dp)
                          , fontWeight = FontWeight.W900)
                  }
//                  Button(onClick = {record.clear()}) {Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove") }
//                  }
                   Divider(thickness = 3.dp)}

                items(record, key = {it.id}){item->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp), shape = RectangleShape) {Row { Column(modifier = Modifier.padding(7.dp)){  Text("Action:${item.action}")
                    Spacer(modifier = Modifier.height(5.dp))
                                                  Text("value:${item.value}",modifier = Modifier.padding().clickable{ val value = item.value
                                                      counter = value})}

                        IconButton(onClick ={record.remove(item)}){ Icon(imageVector=Icons.Default.Delete, contentDescription = "delete") }}}
                } }} } })}


