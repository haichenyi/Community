package com.haichenyi.community.utils.glide

import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * @Author: 海晨忆
 * @Desc: glide工具类
 * @Date: 2020/1/10-15:19
 */
object GlideUtils {
  /**
   * @desc: 正常加载图片
   * @param imageView imageView
   * @param url 图片链接
   */
  fun loadImg(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context).load(MyGlideUrl(url)).into(imageView)
  }

  /**
   * @desc: 加载圆形图片
   * @param imageView imageView
   * @param url 图片链接
   */
  fun loadCircle(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context).load(MyGlideUrl(url)).circleCrop().into(imageView)
  }

  /**
   * @desc: 加载圆角图片
   * @param imageView imageView
   * @param url 图片链接
   * @param radius 圆角半径
   */
  fun loadRoundedCorners(imageView: ImageView, url: String, radius: Int) {
    GlideApp.with(imageView.context).load(MyGlideUrl(url))
      .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
      .into(imageView)
  }

}