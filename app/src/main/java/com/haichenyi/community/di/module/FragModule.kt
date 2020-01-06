package com.haichenyi.community.di.module

import com.haichenyi.community.di.component.FragComponent
import com.haichenyi.community.ui.frag.*
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
  @ContributesAndroidInjector(modules = [FragMainVmModule::class, FragMainModule::class])
  internal abstract fun fragMain(): FragMain

  @FragScoped
  @ContributesAndroidInjector(modules = [FragHomeVmModule::class])
  internal abstract fun fragHome(): FragHome

  @FragScoped
  @ContributesAndroidInjector(modules = [FragSquareVmModule::class])
  internal abstract fun fragSquare(): FragSquare

  @FragScoped
  @ContributesAndroidInjector(modules = [FragMsgVmModule::class])
  internal abstract fun fragMsg(): FragMsg

  @FragScoped
  @ContributesAndroidInjector(modules = [FragAuthorVmModule::class])
  internal abstract fun fragAuthor(): FragAuthor

  @FragScoped
  @ContributesAndroidInjector(modules = [FragTestVmModule::class])
  internal abstract fun fragTest(): FragTest
}