package com.example.chatgptreal

import android.app.DatePickerDialog
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.chatgptreal.ui.theme.ChatgptrealTheme
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun ShowLogin(index:Int,next:MutableState<Boolean>){

    val composableFunctions: List<@Composable () -> Unit> = listOf(
        { Login_Name(next) },
        { Login_Cm_Kg(next) },
        { Login_Birth_Date(next)},
        { Login_Choose_Female(next) }
    )

    composableFunctions[if (index>composableFunctions.size-1) composableFunctions.size-1 else index]() // Invoke the function at the specified index


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Login_Name(next:MutableState<Boolean>){
    Card(
        Modifier
            .fillMaxSize()
            .padding(20.dp), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            val name = remember { mutableStateOf("") }
            Text(
                text = "당신의 이름을 입력하십시요",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp, fontFamily = light,
                modifier = Modifier.padding(20.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))
            val keyboardController = LocalSoftwareKeyboardController.current

            OutlinedTextField(

                value = name.value,
                onValueChange = { newValue ->
                    // Update the text value when it changes
                    name.value = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(110.dp),
                label = {
                    Text(
                        "이름",
                        fontSize = 25.sp,
                        fontFamily = thin,
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = TextStyle(fontSize = 36.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done // Close keyboard on "Next" press
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Close the keyboard when "Done" is pressed
                        keyboardController?.hide()
                    }
                ),
                shape = RoundedCornerShape(15.dp)
            )
            next.value= name.value!=""
        }
    }


}

@Composable
fun Login_Cm_Kg(next:MutableState<Boolean>){
    Card(
        Modifier
            .fillMaxSize()
            .padding(20.dp), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            val cm = remember { mutableStateOf("") }
            val kg = remember { mutableStateOf("") }
            Text(
                text = "당신의 몸무게,키를 입력하십시요",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp, fontFamily = light,
                modifier = Modifier.padding(20.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))
            NumberTextField(text = cm, label = "키","cm")
            NumberTextField(text = kg, label = "몸무게","kg")


        }
    }


}
@Composable
fun Login_Birth_Date(next:MutableState<Boolean>){
    Card(
        Modifier
            .fillMaxSize()
            .padding(20.dp), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
                var selectedDate by remember { mutableStateOf("") }
                Text(
                    text = "당신의 생일을 입력하십시오.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = light,
                    modifier = Modifier.padding(20.dp)
                )
                Spacer(modifier = Modifier.height(60.dp))


                DatePicker(
                    label = "생일",
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* handle keyboard done action */ })
                )
                Spacer(modifier = Modifier.height(30.dp))
                if  (selectedDate!=""){
                    Text(
                        text = "만 ${getYearFromDate(dateString = selectedDate)}세",
                        fontWeight = FontWeight.Bold,
                        fontSize = 60.sp, fontFamily = light,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "맞으시다면 다음 버튼을 클릭해주세요.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, fontFamily = light,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }





        }
    }


}
@Composable
fun Login_Choose_Female(next:MutableState<Boolean>){
    next.value= true
    Card(
        Modifier
            .fillMaxSize()
            .padding(20.dp), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            Text(
                text = "당신의 성별을 선택하십시오.",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp, fontFamily = light,
                modifier = Modifier.padding(20.dp)
            )
            Spacer(modifier = Modifier.height(120.dp))
            var isToggled by remember {
                mutableStateOf(false)
            }
            val animatedValue by animateDpAsState(
                targetValue = if (isToggled) 160.dp else 0.dp,
                animationSpec = tween(durationMillis = 500)
            )
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .clickable {
                        isToggled = !isToggled
                    }){
                Box(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                        .offset(x=animatedValue)
                        .border(
                            border = BorderStroke(2.dp, Color.Black),
                            shape = RoundedCornerShape(125.dp)
                        )) {

                }
            }
            Text(text = if (isToggled)"여성" else "남성",fontWeight = FontWeight.Bold,
                fontSize = 30.sp, fontFamily = Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
            )
        }

    }
}
@Composable
fun getYearFromDate(dateString: String): String=if (dateString=="") "" else (getCurrentYear()-dateString.substring(0, 4).toInt()).toString()

@Composable
fun getCurrentYear(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.YEAR)
}
@Composable
fun NumberTextField(text:MutableState<String>,label:String,addtext:String){
    OutlinedTextField(
        value = text.value,
        onValueChange = { newValue ->
            // Update the text value when it changes
            text.value = newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(110.dp),
        label = {
            Text(
                label,
                fontSize = 25.sp,
                fontFamily = thin,
                textAlign = TextAlign.Center
            )
        },
        textStyle = TextStyle(fontSize = 36.sp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done),
        shape = RoundedCornerShape(25.dp),
        trailingIcon = {
            Text(text = addtext,fontSize = 30.sp)
        }
    )
}
@Composable
fun DatePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "yyyy-MM-dd",
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            onValueChange(LocalDate.of(year, month + 1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth,
    )

    OutlinedTextField(
        value = value,
        onValueChange = { /* handle value change */ },
        enabled = false,
        modifier = Modifier
            .clickable { dialog.show() }
            .fillMaxWidth()
            .padding(20.dp)
            .height(70.dp),
        label = { Text(text = label) },
        shape = RoundedCornerShape(15.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}