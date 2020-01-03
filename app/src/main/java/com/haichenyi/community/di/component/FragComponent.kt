package com.haichenyi.community.di.component

import androidx.databinding.ViewDataBinding
import com.haichenyi.community.base.BaseFrag
import com.haichenyi.community.base.BaseViewModel
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/3-9:49
 */
@Subcomponent
interface FragComponent : AndroidInjector<BaseFrag<ViewDataBinding, BaseViewModel>> {
  @Subcomponent.Factory
  interface Factory : AndroidInjector.Factory<BaseFrag<ViewDataBinding, BaseViewModel>>
}