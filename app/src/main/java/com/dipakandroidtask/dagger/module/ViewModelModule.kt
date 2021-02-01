package com.dipakandroidtask.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dipakandroidtask.dagger.ViewModelFactory
import com.dipakandroidtask.dagger.ViewModelKey
import com.dipakandroidtask.viewmodel.AlbumsViewModel
import com.dipakandroidtask.viewmodel.ImagesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by Dipak Vyas on 31-01-2021.
 */

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindUserViewModel(viewModel: AlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ImagesViewModel::class)
    abstract fun bindSplashViewModel(viewModel: ImagesViewModel): ViewModel
}