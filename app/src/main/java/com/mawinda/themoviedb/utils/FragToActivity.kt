package com.mawinda.themoviedb.utils

import androidx.fragment.app.Fragment

interface FragToActivity {
    fun setTitle(title: String = "")
}

fun Fragment.toActivity(): Lazy<FragToActivity> = lazy {
    try {
        this.requireActivity() as FragToActivity
    } catch (exp: ClassCastException) {
        throw ClassCastException("${requireActivity().localClassName} must implement PassToActivity to use PassToActivity on ${this::class.simpleName}")
    }
}