package com.mawinda.themoviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("movieImage")
fun ImageView.bindImage(url: String?) {
    this.load(url)
}