package com.hujiejeff.wanandroid_compose.ui.common

import android.content.ClipDescription
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.Coil
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.imageLoader
import com.hujiejeff.wanandroid_compose.R

object CoilImageLoader {
    fun initImageLoader(context: Context) {
        Coil.setImageLoader {
            ImageLoader.Builder(context)
                .crossfade(false)
                .allowHardware(true)
                .placeholder(R.drawable.shape_img_place_holder)
                .fallback(R.drawable.shape_img_place_holder)
                .error(R.drawable.shape_img_place_holder)
                .build().apply {
                    Coil.setImageLoader(this)
                }
        }
    }
}


@Composable
fun NewWorkImage(
    modifier: Modifier = Modifier,
    data: Any,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val imagePainter = rememberImagePainter(
        data = data,
        imageLoader = LocalContext.current.imageLoader,
        builder = {
        })
    Image(
        modifier = modifier,
        painter = imagePainter,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}