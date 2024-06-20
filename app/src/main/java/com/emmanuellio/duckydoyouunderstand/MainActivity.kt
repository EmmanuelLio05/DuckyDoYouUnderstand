package com.emmanuellio.duckydoyouunderstand

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.emmanuellio.duckydoyouunderstand.ui.theme.DuckyDoYouUnderstandTheme
import java.util.Locale

private val TAG = "MAIN-ACTIVITY"

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuckyDoYouUnderstandTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SpeechToText(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SpeechToText(
    modifier: Modifier
) {
    val itemPadding = Modifier.padding(2.dp)
    val context = LocalContext.current
    val speech = rememberSaveable {
        mutableStateOf<String?>(null)
    }

    CheckForPermission(context)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Ducky, do you understand?",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = itemPadding
        )

        //TODO Add ducky composable

        Text(
            text = speech.value ?: " ",
            style = MaterialTheme.typography.titleSmall,
            modifier = itemPadding,
            textAlign = TextAlign.Center
        )

        Button(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { getSpeechInput(speech, context = context) },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.microphone),
                contentDescription = "Mic",
                tint = Color.Green,
                modifier = itemPadding
                    .size(56.dp)
            )
        }
    }
}

@Composable
fun CheckForPermission(
    context: Context,
    callBack: () -> Unit = {}
) {
    val RECORD_AUDIO_PERMISSION_GRANTED = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.RECORD_AUDIO
    ) == PackageManager.PERMISSION_GRANTED
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            Log.d("ONBOARDING-SCREEN", "PERMISSION GRANTED: $granted")
            callBack()
        }
    )

    LaunchedEffect(Unit) {
    }
}

private fun getSpeechInput(
    speech: MutableState<String?>,
    context: Context
) {
    val speechRecognizer =
        SpeechRecognizer.createSpeechRecognizer(context)//on below line we are checking if speech recognizer intent is present or not.
    val intent =
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH) //on below line we are calling a speech recognizer intent

    intent.putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
    )
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    speechRecognizer.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) { /*Log.i(TAG, p0.toString())*/
        }

        override fun onBeginningOfSpeech() {
            Log.i(TAG, "Beginning speech.")
        }

        override fun onRmsChanged(p0: Float) { Log.i(TAG, p0.toString()) }

        override fun onBufferReceived(p0: ByteArray?) { Log.i(TAG, p0.toString()) }

        override fun onEndOfSpeech() {
            Log.i(TAG, "Ending speech.")
        }

        override fun onEvent(p0: Int, bundle: Bundle?) {
            Log.i(TAG, bundle.toString())
        }

        override fun onError(p0: Int) {
            Log.i(TAG, p0.toString())
        }

        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        override fun onResults(bundle: Bundle?) {
            //TODO Update state
        }

        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        override fun onPartialResults(bundle: Bundle?) {
            Log.i(TAG, bundle.toString())
        }
    })
    speechRecognizer.startListening(intent)
}