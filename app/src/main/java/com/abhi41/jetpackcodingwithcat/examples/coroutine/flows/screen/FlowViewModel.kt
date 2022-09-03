package com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue

        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
        collectFlatMap()
    }

    private fun collectFlow() {

        viewModelScope.launch {
            countDownFlow.collect { time ->
                delay(1500L)
                println("The current time is $time")

                /*o/p The current time is 10
                     The current time is 9
                     The current time is 8
                     */

            }
        }
        viewModelScope.launch {

            /*    if we used collectLatest then it will get only single emmition
                when you want to emmit one time event then use collectLatest otherwise
                use collect*/

            countDownFlow.collectLatest { time ->
                delay(1500L)
                println("The current time is $time")
            }

            //o/p  The current time is 0
        }

        //---------------------- flow with filter & map---------------------------

        viewModelScope.launch {
            countDownFlow
                .filter { time ->
                    //we only want to receive even value
                    time % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println(time)
                }
                .collect { time ->
                    println("The current time is $time")
                }
        }

        //----------------flow with reduce-----------------------------

        viewModelScope.launch {
            val reduceResult = countDownFlow.reduce { accumulator, value ->
                accumulator + value
            }
            println("The count is $reduceResult")
            //O/P  The count is 45  because accumulator is 4 and value will be 5
        }

        //---------------flow with fold-------------------------------

        viewModelScope.launch {
            val reduceResult = countDownFlow
                .fold(100) { acc, value ->
                    acc + value
                }
            println("The count is $reduceResult")
            //o/p The count is 145  because value start from 100
        }

    }

    private fun collectFlatMap() {

        val flow1 = flow {
            emit(1)
            delay(500L)
            emit(2)
        }
        viewModelScope.launch {
            flow1.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                println("The value is $value")
                /* o/p
                   The value is 2
                   The value is 3
                   The value is 3
                   The value is 4
                 */
            }
        }

    }

    private fun collectFlatMapLatest (){

        val flow1 = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main Dish")
            delay(100L)
            emit("Dessert")
        }
        viewModelScope.launch {
          flow1.onEach {
              println("$it is delivered")
          }
              .collect{
                  println("FLOW: Now eating $it")
                  delay(1500L)
                  println("FLOW: Finished eating $it")
              }

            /* o/p
                  Appetizer is delivered
                  Now eating Appetizer
                  Finished eating Appetizer

                  Main Dish is delivered
                  Now eating Main Dish
                  Finished eating Main Dish

                  Dessert is delivered
                  Now eating Dessert
                  Finished eating Dessert
             */
        }

    }

}