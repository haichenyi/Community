package com.haichenyi.community.base

import com.haichenyi.community.di.component.AppComponent
import com.haichenyi.community.di.component.DaggerAppComponent
import com.haichenyi.community.utils.ToastUtils
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/2 16:09
 */
class BaseApp : DaggerApplication() {
  @Inject
  lateinit var okHttpClient: OkHttpClient

  val appComponent: AppComponent = DaggerAppComponent.factory().create(this) as AppComponent

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

  override fun onCreate() {
    super.onCreate()
    baseApp = this
    ToastUtils.init(this)
  }

  companion object {
    lateinit var baseApp: BaseApp
  }
}