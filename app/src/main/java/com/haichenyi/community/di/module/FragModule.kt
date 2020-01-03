package com.haichenyi.community.di.module

import com.haichenyi.community.di.component.FragComponent
import com.haichenyi.community.ui.frag.FragHome
import com.haichenyi.mvvm.di.scope.FragScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/3-9:49
 */
@Module(subcomponents = [FragComponent::class])
internal abstract class FragModule {
  @FragScoped
  @ContributesAndroidInjector(modules = [FragHomeModule::class])
  internal abstract fun fragHome(): FragHome
}