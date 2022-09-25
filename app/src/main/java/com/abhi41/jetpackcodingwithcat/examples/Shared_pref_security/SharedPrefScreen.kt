package com.abhi41.jetpackcodingwithcat.examples.Shared_pref_security

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.abhi41.jetpackcodingwithcat.examples.Shared_pref_security.SharedPrefConst.FILE_NAME
import com.abhi41.jetpackcodingwithcat.examples.Shared_pref_security.SharedPrefConst.SECRET_KEY

@Composable
fun  SharedPrefScreen() {

    val context = LocalContext.current
    var txtSecret by remember {
        mutableStateOf("")
    }
    var readText by remember {
        mutableStateOf("")
    }
    val materKeyAlias: String by lazy {
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    val sharedPreferances: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            FILE_NAME,
            materKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun loadSecret(): String? =
        sharedPreferances.getString(SECRET_KEY, null)

    fun saveSecret(secret: String) {
        with(sharedPreferances.edit()) {
            putString(SECRET_KEY, secret)
            apply()
        }
    }



    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = txtSecret,
            onValueChange = { txtSecret = it },
            label = { Text(text = "Enter your secret") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { saveSecret(txtSecret) }) {
            Text(text = "Save text")
        }
        Button(onClick = {  readText = loadSecret()?:"Read Text" }) {
            Text(text = "Read Text")
        }

        Text(text = readText)
    }

}

