package com.mawinda.themoviedb.utils

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import coil.load
import coil.size.Scale
import com.mawinda.data.local.entities.Genre
import com.mawinda.themoviedb.R
import com.mawinda.themoviedb.databinding.GenreItemBinding

@BindingAdapter("movieImage")
fun ImageView.bindImage(url: String?) {
    this.load(url) {
        crossfade(true)
        scale(Scale.FILL)
        //transformations(RoundedCornersTransformation(0.0f))
    }

}


@BindingAdapter("backImage")
fun ImageView.bindBackImage(url: String?) {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.blue_rec_img)
        error(R.drawable.blue_rec_img)
        scale(Scale.FILL)
        //transformations(RoundedCornersTransformation(0.0f))
    }
}

@BindingAdapter("genres")
fun LinearLayout.bindGenres(genres: List<Genre>?) {
    if (genres.isNullOrEmpty())
        return

    this.removeAllViews()
    val layoutInflater = LayoutInflater.from(this.context)
    genres.forEach {
        val view: GenreItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.genre_item, null, false)
        view.genre = it
        this.addView(view.root)
    }
}