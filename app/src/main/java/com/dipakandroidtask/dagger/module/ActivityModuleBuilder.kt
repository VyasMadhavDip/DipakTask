package com.dipakandroidtask.dagger.module

import com.dipakandroidtask.ui.activity.AlbumsActivity
import com.dipakandroidtask.ui.activity.ImagesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Dipak Vyas on 31-01-2021.
 */
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeAlbumsActivity(): AlbumsActivity

    @ContributesAndroidInjector()
    abstract fun contributeImagesActivity(): ImagesActivity

}