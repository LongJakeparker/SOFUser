package com.example.sofuser.util

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageUtil {
    fun loadAvatar(image: String?, view: ImageView) {
        if (image == null)
            return

        Glide.with(view.context).load(image).into(view)
    }
}