package com.dipakandroidtask.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dipakandroidtask.data.Failure


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
abstract class BaseViewModel : ViewModel() {
    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }
}