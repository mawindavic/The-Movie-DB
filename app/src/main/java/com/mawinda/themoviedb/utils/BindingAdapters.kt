package com.mawinda.themoviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

@BindingAdapter("movieImage")
fun ImageView.bindImage(url: String?) {
    this.load(url) {
        crossfade(true)
        crossfade(500)
        //transformations(RoundedCornersTransformation(25f))
    }
}