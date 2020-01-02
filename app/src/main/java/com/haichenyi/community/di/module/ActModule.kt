package com.haichenyi.community.di.module

import com.haichenyi.community.di.component.ActComponent
import com.haichenyi.community.ui.act.MainAct
import com.haichenyi.mvvm.di.scope.ActScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/2-17:01
 */
@Module(subcomponents = [ActComponent::class])
internal abstract class ActModule {
  @ActScoped
  @ContributesAndroidInjector
  internal abstract fun mainAct(): MainAct
}