package com.dipakandroidtask.dagger.module

import com.dipakandroidtask.BuildConfig
import com.dipakandroidtask.data.remote.RequestInterface
import com.dipakandroidtask.utils.Constants.Companion.BASE_URL
import com.dipakandroidtask.utils.Constants.Companion.TIME_OUT_CONNECT
import com.dipakandroidtask.utils.Constants.Companion.TIME_OUT_READ
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Dipak Vyas on 31-01-2021.
 */
@Module
class NetworkModule() {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    private fun createClient(): OkHttpClient {
        OkHttpClient.Builder().apply {
            addInterceptor(logger())
            connectTimeout(TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
            readTimeout(TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
            return build()
        }
    }

    private fun logger(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        return loggingInterceptor
    }

}
