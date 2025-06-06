package com.example.interviewprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            InterviewPrepTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, viewModel: ProfileViewModel = hiltViewModel()) {

    val profile = viewModel.profile.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val error = viewModel.error.collectAsState()

    if (isLoading.value) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

    } else if (error.value?.isNotEmpty() == true) {
        Text(text = error.value ?: "", modifier = Modifier.align(Alignment.Center))

    } else {
        LazyColumn {
            items(profile.value?.size ?: 0) { index ->
                ProfileItem(profilePage = profile.value[index])
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewPrepTheme {
        Greeting("Android")
    }
}