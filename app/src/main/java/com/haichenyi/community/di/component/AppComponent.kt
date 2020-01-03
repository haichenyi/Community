package com.haichenyi.community.di.component

import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.data.DataCompat
import com.haichenyi.community.di.module.*
import com.haichenyi.community.di.module.ActModule
import com.haichenyi.community.di.module.FragModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [AndroidSupportInjectionModule::class,
    ActModule::class,
    HttpModule::class,
    AppModule::class,
    VmFactoryModule::class,
    FragModule::class
  ]
)
interface AppComponent : AndroidInjector<BaseApp> {
  @Component.Factory
  interface Factory : AndroidInjector.Factory<BaseApp>

  fun dataCompat(): DataCompat
}