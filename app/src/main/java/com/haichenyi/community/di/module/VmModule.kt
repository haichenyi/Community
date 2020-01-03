package com.haichenyi.community.di.module

import androidx.lifecycle.ViewModel
import com.haichenyi.community.di.scope.VmKey
import com.haichenyi.community.vm.FragHomeVm
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @Author: 海晨忆
 * @Desc: 所有fragment对应的vm获取方式di层
 * @Date: 2020/1/3-9:47
 */
@Module
abstract class FragHomeModule {
  @Binds
  @IntoMap
  @VmKey(FragHomeVm::class)
  abstract fun getViewModel(fragHomeVm: FragHomeVm): ViewModel
}