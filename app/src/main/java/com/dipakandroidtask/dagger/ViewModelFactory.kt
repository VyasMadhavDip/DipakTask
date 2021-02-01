package com.dipakandroidtask.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by Dipak Vyas on 31-01-2021.
 */

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Singleton
@Suppress("UNCHECKED_CAST")
class ViewModelFactory
@Inject constructor(private val creators: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?:
            creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value ?:
            throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        return try { creator.get() as T }
        catch (e: Exception) { throw RuntimeException(e) }
    }
}