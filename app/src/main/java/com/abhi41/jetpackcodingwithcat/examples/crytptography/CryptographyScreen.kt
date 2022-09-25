package com.abhi41.jetpackcodingwithcat.examples.crytptography

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Composable
fun CryptographyScreen() {
    val cryptoManager = CryptoManager()
    var messageToEncrypt by remember {
        mutableStateOf("")
    }
    var messageToDecrypt by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        TextField(
            value = messageToEncrypt,
            onValueChange = { messageToEncrypt = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Encrypt string") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = {
                val bytes = messageToEncrypt.encodeToByteArray()
                val file = File(context.filesDir, "secret.txt")
                if(!file.exists()) {
                    file.createNewFile()
                }
                val fos = FileOutputStream(file)

                messageToDecrypt = cryptoManager.encrypt(
                    bytes = bytes,
                    outputStream = fos
                ).decodeToString()
            }) {
                Text(text = "Encrypt")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                val file = File(context.filesDir, "secret.txt")
                messageToEncrypt = cryptoManager.decrypt(
                    inputStream = FileInputStream(file)
                ).decodeToString()
            }) {
                Text(text = "Decrypt")
            }
        }
        Text(text = messageToDecrypt)
    }


}