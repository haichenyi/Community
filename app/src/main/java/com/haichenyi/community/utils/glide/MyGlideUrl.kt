package com.haichenyi.community.utils.glide

import com.bumptech.glide.load.model.GlideUrl

/**
 * @Author: 海晨忆
 * @Desc: 自定义缓存图片的key
 * @Date: 2020/1/10-16:20
 */
class MyGlideUrl(val url: String) : GlideUrl(url) {

  override fun getCacheKey(): String {
    return getKey()
  }

  /**
   * @desc: 自定义缓存图片的key，很多情况下，图片链接会加上时间戳，同一张图片，时间戳不同
   * 这样会造成key不相同，每次都会访问网络，这里，我们只需要把图片连接的时间戳去掉即可
   * @return: 返回值就是你这张图片需要key
   */
  private fun getKey(): String {
    return url
  }
}