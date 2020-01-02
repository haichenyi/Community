package com.haichenyi.community.di.component

import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.di.module.ActModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [AndroidSupportInjectionModule::class,
    ActModule::class
  ]
)
interface AppComponent : AndroidInjector<BaseApp> {
  @Component.Factory
  interface Factory : AndroidInjector.Factory<BaseApp>
}