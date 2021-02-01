package com.dipakandroidtask.dagger

import com.dipakandroidtask.TaskApplication
import com.dipakandroidtask.dagger.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DataModule::class,
        NetworkModule::class,
        ActivityModuleBuilder::class, ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

    fun inject(app: TaskApplication)
}