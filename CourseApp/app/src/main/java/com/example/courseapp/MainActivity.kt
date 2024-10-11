package com.example.courseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseapp.data.courseData
import com.example.courseapp.model.Topic
import com.example.courseapp.ui.theme.CourseAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                    CourseApp()
                }
            }
        }
    }
}



@Composable
fun CourseItem(course: Topic,modifier: Modifier = Modifier) {
    Card (modifier = modifier) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Box {
                Image(
                    painter = painterResource(course.imageResourceId),
                    contentDescription = null,

                    modifier = Modifier
                        .padding(8.dp)
                        .size(68.dp, 68.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop

                )
            }



            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(course.stringResourceId))
                    Text(text = course.courseCount.toString())
                }
            }

        }
    }
}

@Composable
fun CourseList(courses: List<Topic>) {

    LazyColumn (horizontalAlignment = Alignment.CenterHorizontally){
        for (i in 0..<courses.size-1 step 2) {
            item {
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()){
                    CourseItem(courses[i],modifier = Modifier.weight(1f))
                    CourseItem(courses[i + 1],modifier = Modifier.weight(1f))
                }
            }
        }
    }



}

@Composable
fun CourseApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        CourseList(
            courseData().getData(),)
    }
}

@Preview
@Composable
fun PreviewCourseApp() {
    CourseAppTheme {
        CourseApp()
    }
}