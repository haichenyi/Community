package com.haichenyi.community.di.module

import com.haichenyi.community.data.DataCompat
import com.haichenyi.community.data.http.HttpHelper
import com.haichenyi.community.data.http.HttpImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  internal fun httpHelper(httpImpl: HttpImpl): HttpHelper = httpImpl

  @Provides
  @Singleton
  internal fun dataCompat(httpHelper: HttpHelper): DataCompat = DataCompat(httpHelper)

}