package com.moyinoluwa.hiltsampleapplication.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader {

    override fun loadCircularImage(@DrawableRes resourceId: Int, view: ImageView) {
        Glide.with(view)
            .load(resourceId)
            .transform(CircleCrop())
            .into(view)
    }
}
