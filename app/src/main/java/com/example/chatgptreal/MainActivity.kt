@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.chatgptreal

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatgptreal.ui.theme.ChatgptrealTheme
import com.example.chatgptreal.ui.theme.DarkGreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatgptrealTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}
val Bold = FontFamily(
    Font(resId = R.font.spoqa_han_sans_bold, weight = FontWeight.Normal)
)
val light = FontFamily(
    Font(resId = R.font.spoqa_han_sans_light, weight = FontWeight.Normal)
)
val regul = FontFamily(
    Font(resId = R.font.spoqa_han_sans_regul, weight = FontWeight.Normal)
)
val thin = FontFamily(
    Font(resId = R.font.spoqa_han_sans_thin, weight = FontWeight.Normal)
)
@SuppressLint("ShowToast")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Greeting() {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize(),) {

        val pagerstate= rememberPagerState()
        var next = remember {
            mutableStateOf(false)
        }
        HorizontalPager(
            count = 10,

            state = pagerstate,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
            ,
            content = {
                ShowLogin(index = it,next)
            },

            )
        Card(
            Modifier
                .fillMaxSize()
                .padding(20.dp), shape = RoundedCornerShape(20.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                val coroutineScope = rememberCoroutineScope()

                TestButton(text = "다음",next.value) {
                    if (next.value){
                        coroutineScope.launch {
                            pagerstate.animateScrollToPage(pagerstate.currentPage+1)
                        }
                    }else{
                        coroutineScope.launch {
                            Toast.makeText(context,"모든 질문에 답을 하셨나요",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }

    }


}


@Composable
fun TestButton(text:String,next:Boolean=false,onclick: ()-> Unit){


    Button(onClick = onclick, colors = ButtonDefaults.buttonColors(
        backgroundColor = Color(0xFFADD8E6), // Set the background color
    ),
    shape = RoundedCornerShape(25.dp),
    modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        .alpha(if (next)1f else 0.3f)) {
        Text(text = text, fontWeight = FontWeight.ExtraBold, fontSize = 40.sp, fontFamily = Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatgptrealTheme {
        Greeting()
    }
}