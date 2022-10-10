package com.abhi41.jetpackcodingwithcat.examples.sealed_class

import androidx.compose.runtime.Composable

@Composable
fun SealedExample() {

    //what is sealed class ?
    /*
        Sealed class is just a abstract class where all the children class that inherit from
        this class are known at compile time.that means no other module can extend this class.
        this work only that specific module where it declares.
     */
    val error: HttpError = HttpError.Unautherized

    when (error) {
        HttpError.NotFound -> Unit
        HttpError.Unautherized -> Unit
    }

    val errorEnum: HttpErrorEnum = HttpErrorEnum.NotFound

    when (errorEnum) {
        HttpErrorEnum.Unauthorized -> Unit
        HttpErrorEnum.NotFound -> Unit
    }

}

sealed class HttpError(val code: Int) {
    object Unautherized : HttpError(401)
    object NotFound : HttpError(404)

    //we can also used data class
    data class InvalidToken(val reason: String) : HttpError(403)
}

enum class HttpErrorEnum(val code: Int) {
    Unauthorized(401),
    NotFound(404);
}

sealed interface HttpErrorInterface {
    //only difference is interface class does not contain constructor

    data class Unatherized(val reason: String): HttpErrorInterface
    object NotFound: HttpErrorInterface
}