package com.dipakandroidtask.dagger.module

import android.content.Context
import com.dipakandroidtask.TaskApplication
import com.dipakandroidtask.utils.NetworkConnectivity
import com.dipakandroidtask.utils.NetworkHandler
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
@Module
class AppModule() {

    @Provides
    fun provideContext(): Context {
        return TaskApplication.context
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(): NetworkConnectivity {
        return NetworkHandler(TaskApplication.context)
    }
}