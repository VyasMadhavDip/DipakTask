package com.dipakandroidtask.extention

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
inline fun <reified T : ViewModel> AppCompatActivity.viewModel(
    factory: ViewModelProvider.Factory
): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

fun AppCompatActivity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}