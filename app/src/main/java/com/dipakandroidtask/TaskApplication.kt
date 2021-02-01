package com.dipakandroidtask

import android.app.Application
import android.content.Context
import com.dipakandroidtask.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
class TaskApplication : Application(), HasAndroidInjector {

    companion object {
        lateinit var context: Context
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDagger()
    }

    private fun initDagger() {
        DaggerAppComponent.builder().build().inject(this)
    }
}