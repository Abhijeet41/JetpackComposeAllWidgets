package com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.combine_zip_merge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.combine_zip_merge.model.Post
import com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.combine_zip_merge.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CombineZipMergeViewModel : ViewModel() {
    private val isAuthenticated = MutableStateFlow(true)
    private val user = MutableStateFlow<User?>(null)
    private val posts = MutableStateFlow(emptyList<Post>())

    private val _profileState = MutableStateFlow<ProfileState?>(null)
    val profileState = _profileState.asStateFlow()

    private val flow1 = (1..10).asFlow().onEach { delay(1000L) }
    private val flow2 = (10..20).asFlow().onEach { delay(300L) }
    var numberString by mutableStateOf("")
        private set

    init {
        //for e.g if user or posts stateflows are getting changed then update _profileState
        user.combine(posts) { user, posts ->
            _profileState.value = _profileState.value?.copy(
                profilePicUrl = user?.profileUrl,
                username = user?.username,
                description = user?.description,
                posts = posts
            )
        }.launchIn(viewModelScope)
        //----- this way also we can define--------------------
        viewModelScope.launch {
            user.combine(posts) { user, posts ->
                _profileState.value = _profileState.value?.copy(
                    profilePicUrl = user?.profileUrl,
                    username = user?.username,
                    description = user?.description,
                    posts = posts
                )
            }.collect()
        }

        /* lets you have to combine with 3rd flow or more then 2 flow current
           user is authenticated or not. if user is not authenticated we also
           don't want to listen to profile changes. if its not authenticated
           then give him message please authenticate first
         */

        isAuthenticated.combine(user) { isAuthenticated, user ->
            //if its authenticated then only pass user and let him update user and posts
            if (isAuthenticated) user else null
        }.combine(posts) { user: User?, posts: List<Post> ->
            user.let { //check user is null or not
                _profileState.value = _profileState.value?.copy(
                    profilePicUrl = user?.profileUrl,
                    username = user?.username,
                    description = user?.description,
                    posts = posts
                )
            }
        }.launchIn(viewModelScope)

        //-------------------example of zip operator-----------------------------

        /* Note: How zip is different from combine?
            zip operator will w8 for emmition of flow1 and flow2 then  only
            below function will gets triggered
         */

        flow1.zip(flow2) { number1: Int, number2: Int ->
            numberString = numberString +"($number1,$number2)\n"
        }.launchIn(viewModelScope)


        //------------------ Merge example----------------------------------------

        /* In this we can merge multiple flows into flow. currently we have only 2 flows
            but you could pass as many as you like
         */


    }//init

}