package com.dipakandroidtask.dagger.module

import com.dipakandroidtask.data.repository.DataRepository
import com.dipakandroidtask.data.repository.DataRepositorySource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDataRepository(dataSource: DataRepository): DataRepositorySource = dataSource

}