package com.haichenyi.community.utils.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.utils.logV
import java.io.InputStream

/**
 * @Author: 海晨忆
 * @Desc: glide的帮助类
 * @Date: 2020/1/9-16:38
 */
@GlideModule
class GlideModule : AppGlideModule() {
  /**
   * 50M
   */
  private val memoryCache: Long = 50.shl(20)
  /**
   * 200M
   */
  private val diskCache: Long = 200.shl(20)

  override fun applyOptions(context: Context, builder: GlideBuilder) {
    val strategy = GlideExecutor.UncaughtThrowableStrategy {
      logV(it.message ?: "")
      it.printStackTrace()
    }
    builder.apply {
      //log日志等级
      setLogLevel(Log.DEBUG)
      //设置bitmap池为LruBitmapPool，并设置大小
      setBitmapPool(LruBitmapPool(memoryCache))
      //设置内存缓存为LruResourceCache，并设置大小
      setMemoryCache(LruResourceCache(memoryCache))
      //设置磁盘缓存，默认目录是app包下的cache目录，名字叫Glide，大小是：diskCache
      setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, "Glide", diskCache))
      //设置异常捕获策略
      setDiskCacheExecutor(GlideExecutor.newDiskCacheBuilder().setUncaughtThrowableStrategy(strategy).build())
      setSourceExecutor(GlideExecutor.newSourceBuilder().setUncaughtThrowableStrategy(strategy).build())
    }
  }

  override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
    //这里把glide图片的加载方式换成OkHttp
    val factory = OkHttpUrlLoader.Factory(BaseApp.baseApp.okHttpClient)
    registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
  }

  /**
   * @desc: 设置清单解析，设置为false，避免添加相同的modules两次
   * @return: boolean
   */
  override fun isManifestParsingEnabled(): Boolean = false
}