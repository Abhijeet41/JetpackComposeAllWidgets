package com.abhi41.jetpackcodingwithcat.examples.widgets.date_time_picker

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DateAndTimePicker() {
    val context = LocalContext.current
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val date = remember { //date api from desugar_jdk_libs
        // LocalDate.now().dayOfWeek
        //  LocalDate.now().dayOfMonth
        //   LocalDate.now().dayOfYear
        // LocalDateTime.now().plusHours(15)
        ZonedDateTime.now()
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }

    val formattedDate2 = remember {
        DateTimeFormatter.ofPattern("dd MM yyy")
            .format(date)
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = {
            dateDialogState.show()
        }) {
            Text(text = "Pick date")
        }

        Text(text = formattedDate)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Pick time")
        }
        Text(text = formattedTime)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = formattedDate2.toString())
    }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        ),
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(context, "Clicked Ok", Toast.LENGTH_SHORT).show()
            }
            negativeButton(text = "cancle") {
                dateDialogState.hide()
            }
        },
        onCloseRequest = {
            //we can use in firebase analytics that user close dialog with out selecting any thing
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            //allowedDateValidator with the help of this we can allows user to select even or odd date
            allowedDateValidator = {
                it.dayOfMonth % 2 == 0 //allows user to select even date
                // it.dayOfMonth % 2 == 1 //allows user to select odd date
            }
        ) {
            pickedDate = it
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        ),
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(context, "Clicked Ok", Toast.LENGTH_SHORT).show()
            }
            negativeButton(text = "cancle") {
                dateDialogState.hide()
            }
        },
        onCloseRequest = {
            //we can use in firebase analytics that user close dialog with out selecting any thing
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick a time",
            //timeRange this will allows user to select time range midnight to noon only
            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
        ) {
            pickedTime = it
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DateAndTimePickerPreview() {
    DateAndTimePicker()
}