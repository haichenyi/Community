package com.haichenyi.community.di.module

import android.content.Context
import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.data.http.HttpApi
import com.haichenyi.community.data.http.HttpLoggingInterceptor
import com.haichenyi.community.data.http.HttpProtocol
import com.haichenyi.community.data.http.LiveDataCallAdapterFactory
import com.haichenyi.community.utils.LogUtil
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

/**
 * @Author: 海晨忆
 * @Desc: 网络请求module
 * @Date: 2020/1/2-17:26
 */
@Module
class HttpModule {
  @Provides
  internal fun getContext(app: BaseApp): Context = app.applicationContext

  @Provides
  @Singleton
  internal fun okHttpClient(context: Context): OkHttpClient = TrustManager().let {
    OkHttpClient.Builder().apply {
      val sslContext = SSLContext.getInstance("TLS")
      sslContext.init(null, arrayOf(it), SecureRandom())
      this.sslSocketFactory(sslContext.socketFactory, it)
      this.hostnameVerifier(HostnameVerifier { _, _ -> true })
      val httpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
          override fun log(message: String) {
            LogUtil.v(LogUtil.LOG_WZ, message)
          }
        })
      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      this.addInterceptor(httpLoggingInterceptor)
      val cookieJar = object : CookieJar {
        override fun loadForRequest(url: HttpUrl): List<Cookie> = emptyList()

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        }
      }
      this.cookieJar(cookieJar)
      this.cache(Cache(File(context.externalCacheDir, "OkHttp"), 100 * 1024 * 1024))
      this.connectTimeout(10, TimeUnit.SECONDS)
      this.readTimeout(10, TimeUnit.SECONDS)
      this.writeTimeout(10, TimeUnit.SECONDS)
    }
      .build()
  }

  @Provides
  @Singleton
  internal fun httpApi(context: Context): HttpApi = Retrofit.Builder().let {
    it.addConverterFactory(GsonConverterFactory.create())
    it.addCallAdapterFactory(LiveDataCallAdapterFactory())
    it.client(okHttpClient(context))
    it.baseUrl(HttpProtocol.HTTP_HOST)
    it.build()
      .create(HttpApi::class.java)
  }

  companion object {
    class TrustManager : X509TrustManager {
      override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit

      override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit

      override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    }
  }
}