package uz.doston.messanger.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.messanger.Database.AppDataBase
import uz.doston.messanger.Database.Message
import uz.doston.messanger.R


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(name: String, navController: NavController) {

    var msg by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    var messages by remember { mutableStateOf(emptyList<Message>()) }
    AppDataBase.getMessages(name, AppDataBase.getSavedUser(context)) { list ->
        messages = list
    }



    Scaffold(containerColor = Color(0xFF6BB185), topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0, 176, 255, 255)
        ), title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigate("home_screen") }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = Color.White,
                    )
                }
                Text(text = name, color = Color.White)
            }
        })
    }, bottomBar = {
        BottomAppBar(containerColor = Color(23, 33, 43)) {
            OutlinedTextField(
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White
                ),
                value = msg,
                onValueChange = {
                    msg = it
                },
                label = { Text(text = "Your Message") },
                placeholder = { Text(text = "Message") },
            )
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {
                    AppDataBase.sendMessage(AppDataBase.getSavedUser(context), name, msg.text)
                    AppDataBase.getMessages(name, AppDataBase.getSavedUser(context)) { list ->
                        messages = list
                    }
                    msg = TextFieldValue()
                }) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send Icon",
                        tint = Color(108, 120, 131),
                    )
                }
            }
        }
    }) { innerPadding ->
        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            items(messages) { item ->
                item.msg?.let { it1 ->
                    item.date?.let { it2 ->
                        MessageItem(
                            msg = it1,
                            time = it2,
                            position = item.from == AppDataBase.getSavedUser(context)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun MessageItem(msg: String, time: String, position: Boolean){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        if (position) {
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Surface(
//                    #EEFFDE
                    modifier = Modifier
                        .background(Color(0xFFEEFFDE), RoundedCornerShape(33))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        modifier = Modifier.background(Color(0xFFEEFFDE)),
                        fontSize = 25.sp,
                        text = msg,
                        color = Color.Black,
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    fontSize = 17.sp,
                    text = time,
                    color = Color.Black
                )
            }
        } else {
            Column(horizontalAlignment = Alignment.Start) {
                Surface(
                    modifier = Modifier
                        .background(Color(255, 255, 255, 255), RoundedCornerShape(33))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        modifier = Modifier.background(Color(255, 255, 255, 255)),
                        fontSize = 25.sp,
                        text = msg,
                        color = Color.Black,
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    fontSize = 17.sp,
                    text = time,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}