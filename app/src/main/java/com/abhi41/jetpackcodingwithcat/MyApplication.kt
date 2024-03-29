package com.abhi41.jetpackcodingwithcat

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.abhi41.jetpackcodingwithcat.network.DemoApi
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

    @HiltAndroidApp
    class MyApplication : Application() {

    }

/* we have described how to provide now this instance of our demoapi
    to customworker
 */
  /*  class CustomWorkerfactory @Inject constructor(
        private val api: DemoApi
    ) : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker = CustomWorker(api, appContext, workerParameters)

    }
*/



