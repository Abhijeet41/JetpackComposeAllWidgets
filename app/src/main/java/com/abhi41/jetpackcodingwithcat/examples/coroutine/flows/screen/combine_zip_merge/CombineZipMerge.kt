package com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.combine_zip_merge

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CombineZipMerge() {

    val viewmodel = viewModel<CombineZipMergeViewModel>()

    Text(text = viewmodel.numberString)

    // output:
    /*
        (1,10)
        (2,11)
        (3,12)
        (4,13)
        (5,14)
        (6,15)
        (7,16)
        (8,17)
        (9,18)
        (10,19)

     */
}