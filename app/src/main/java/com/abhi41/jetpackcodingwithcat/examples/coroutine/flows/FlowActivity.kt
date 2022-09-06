package com.abhi41.jetpackcodingwithcat.examples.coroutine.flows

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.FlowViewModel
import com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.StateFlowViewModel
import com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.ui.theme.JetpackCodingWithCatTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowActivity : ComponentActivity() {
    val stateFlowViewModel: StateFlowViewModel by viewModels()

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //this is a wrong way to maintain state after configuration change lik erotate device
        lifecycleScope.launch {
            stateFlowViewModel.stateFlow.collectLatest { num ->
                //  binding.tvCounter.text = num.toString()
            }
        }

        //this is a correct way
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlowViewModel.stateFlow.collectLatest { num ->
                    //  binding.tvCounter.text = num.toString()
                }
            }
        }
        //this one is better way with the help of extension function it will reduces the size
        collectLatestLifecycleFlow(stateFlowViewModel.stateFlow){ num->
            //binding.tvCounter.text = num.toString()
        }

        setContent {
            JetpackCodingWithCatTheme {
                // A surface container using the 'background' color from the theme

                val viewmodel = viewModel<FlowViewModel>()

                val time = viewmodel.countDownFlow.collectAsState(initial = 10)

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = time.value.toString(),
                            fontSize = 30.sp
                        )

                        Button(onClick = {
                            stateFlowViewModel.incrementCounter()
                        }) {
                            Text(text = "Counter: ${stateFlowViewModel.stateFlow.value}")
                        }

                    }
                }
            }
        }
    }
}

//extension fun

fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {

    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            flow.collectLatest (collect)
        }
    }

}