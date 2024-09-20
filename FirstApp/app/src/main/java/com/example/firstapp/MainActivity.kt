package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    //GreetingImage(message = stringResource(R.string.happy_birthday_text),
                    //    stringResource(R.string.signature_text),
                    //    modifier = Modifier.padding(8.dp))
                    //)
                    //GreetingScreen(title = stringResource(R.string.title_text),
                    //    passage1 = stringResource(R.string.passage1_text),
                    //    passage2 = stringResource(R.string.passage2_text)
                    //)
                    FinalScreen(modifier = Modifier)

                }
            }
        }

    }
}


@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier){
    Column (verticalArrangement = Arrangement.Center,
        modifier=modifier){
        Text(
            text = message,
            color = Color.Blue,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
    }
}


@Composable
fun GreetingScreen(title: String, passage1: String, passage2: String, modifier: Modifier = Modifier){
    Column (verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier){
        val image = painterResource(R.drawable.bg_compose_background);
        Image(
            painter = image,
            contentDescription = null,
        )

        Text(
            text = title,
            color = Color.Blue,
            fontSize = 24.sp,
            modifier = Modifier
                    .padding(16.dp)
        )

        Text(
            text = passage1,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 16.dp)


        )
        Text(
            text = passage2,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)

        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty);
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)

        )
    }
}

@Composable
fun FinalScreen(modifier: Modifier = Modifier,message1: String="All tasks completed", message2: String="Nice work!") {
    val image = painterResource(R.drawable.ic_task_completed);

    Box(modifier=modifier, contentAlignment = Alignment.Center) {

        Column {
            Image(
                painter = image,
                contentDescription = null,
                )
            Text(
                text = message1,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = message2,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier

                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    FirstAppTheme  {
        GreetingImage(
            message = stringResource(R.string.happy_birthday_text),
            from = stringResource(R.string.signature_text)
        )
    }
}