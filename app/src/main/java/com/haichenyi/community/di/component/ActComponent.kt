package com.haichenyi.community.di.component

import androidx.databinding.ViewDataBinding
import com.haichenyi.community.base.BaseAct
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/2-16:53
 */
@Subcomponent
interface ActComponent : AndroidInjector<BaseAct<ViewDataBinding>> {

  @Subcomponent.Factory
  interface Factory : AndroidInjector.Factory<BaseAct<ViewDataBinding>>


}