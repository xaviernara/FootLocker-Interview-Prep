package com.example.interviewprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.interviewprep.model.ProfilePage
import com.example.interviewprep.ui.theme.InterviewPrepTheme
import com.example.interviewprep.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel: ProfileViewModel by viewModels()
            InterviewPrepTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: ProfileViewModel? = null, modifier: Modifier) {

    val profile = viewModel?.profile?.collectAsState()
    val isLoading = viewModel?.isLoading?.collectAsState()
    val error = viewModel?.error?.collectAsState()

    if (isLoading?.value == true) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }


    } else if (error?.value?.isNotEmpty() == true) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = error.value ?: "")
        }

    } else {
        LazyColumn {
            items(profile?.value?.size ?: 0) { index ->
                ProfileItem(profilePage = profile?.value?.get(index) ?: ProfilePage())
            }
        }
    }

}

@Composable
fun ProfileItem(profilePage: ProfilePage) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Text(
            text = profilePage.data?.get(0)?.first_name + " " + profilePage.data?.get(0)?.last_name,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = profilePage.data?.get(0)?.email ?: "",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
        Text(
            text = profilePage.support?.text ?: "",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal
        )
    }
}

@Composable
fun ToDoList(){
    var toDoList  = remember{ mutableStateListOf("Task 1", "Task 2", "Task 3") }

    LazyColumn(){
        items(toDoList.size, key = { index -> toDoList[index]}){ index ->
            ToDoItem(text = toDoList[index], toDoList = toDoList)
        }
        item{

        }
    }

}

@Composable
fun ToDoItem(text: String, toDoList: MutableList<String> = mutableStateListOf()) {
    var checked by remember { mutableStateOf(false) }
    var changedText by remember { mutableStateOf(text) }

    Card(
        shape = CardDefaults.elevatedShape,
    ){
        TextField(
            leadingIcon = {
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = !checked
                    },
                    interactionSource = remember { MutableInteractionSource() }
                )
            },
            value = text,
            onValueChange = {
                changedText = it
//                toDoList.add(it)
            },
            placeholder = {
                Text(text = "Enter a task")
            },
            label = {
                Text(text = "Enter a task")
            }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    //call vm fun to add to mutable state list
                    toDoList.add(text)
                    checked = toDoList.contains(changedText)
                          },
                content = {

                    Text(text = "Add Task")
                },
                colors = ButtonDefaults.buttonColors(Color.Green)
            )
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    //call vm fun to remove to mutable state list
                toDoList.remove(text) },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(text = "Delete")
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    InterviewPrepTheme {
//        Greeting(modifier = Modifier)
//    }
//}

@Preview(showBackground = true)
@Composable
fun ToDoListPreview() {
   ToDoList()
}