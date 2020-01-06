package com.haichenyi.community.di.module

import androidx.lifecycle.ViewModel
import com.haichenyi.community.di.scope.VmKey
import com.haichenyi.community.vm.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @Author: 海晨忆
 * @Desc: 所有fragment对应的vm获取方式di层
 * @Date: 2020/1/3-9:47
 */
@Module
abstract class FragMainVmModule {
  @Binds
  @IntoMap
  @VmKey(FragMainVm::class)
  abstract fun getViewModel(fragMainVm: FragMainVm): ViewModel

}

@Module
abstract class FragHomeVmModule {
  @Binds
  @IntoMap
  @VmKey(FragHomeVm::class)
  abstract fun getViewModel(fragHomeVm: FragHomeVm): ViewModel

}

@Module
abstract class FragSquareVmModule {
  @Binds
  @IntoMap
  @VmKey(FragSquareVm::class)
  abstract fun getViewModel(fragSquareVm: FragSquareVm): ViewModel
}

@Module
abstract class FragMsgVmModule {
  @Binds
  @IntoMap
  @VmKey(FragMsgVm::class)
  abstract fun getViewModel(fragMsgVm: FragMsgVm): ViewModel
}

@Module
abstract class FragAuthorVmModule {
  @Binds
  @IntoMap
  @VmKey(FragAuthorVm::class)
  abstract fun getViewModel(fragAuthorVm: FragAuthorVm): ViewModel
}

@Module
abstract class FragTestVmModule {
  @Binds
  @IntoMap
  @VmKey(FragTestVm::class)
  abstract fun getViewModel(fragTestVm: FragTestVm): ViewModel
}