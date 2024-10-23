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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    val context = LocalContext.current
    val speech = rememberSaveable {
        mutableStateOf<String?>(null)
    }

    CheckForPermission(context)
    ChatScreen(
        speech,
        modifier
    ) {
        getSpeechInput(speech, context = context)
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
        if (!RECORD_AUDIO_PERMISSION_GRANTED) {
            Log.i("MAIN-ACTIVITY", "REQUESTING PERMISSION.")
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        } else callBack()
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
    intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 2000)
    speechRecognizer.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) { /*Log.i(TAG, p0.toString())*/
        }

        override fun onBeginningOfSpeech() {
            Log.i(TAG, "Beginning speech.")
        }

        override fun onRmsChanged(p0: Float) { /*Log.i(TAG, p0.toString())*/
        }

        override fun onBufferReceived(p0: ByteArray?) { /*Log.i(TAG, p0.toString())*/
        }

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
            val result = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

            Log.i(TAG, result?.get(0)?.toString()?:"empty")
            if (result != null) {
                speech.value = result[0]
                Log.i(TAG, result[0])
            }
        }

        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        override fun onPartialResults(bundle: Bundle?) {
            Log.i(TAG, bundle.toString())
        }
    })
    speechRecognizer.startListening(intent)
}