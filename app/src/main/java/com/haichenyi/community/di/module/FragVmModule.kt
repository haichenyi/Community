package com.haichenyi.community.di.module

import androidx.fragment.app.Fragment
import com.haichenyi.community.R
import com.haichenyi.community.common.createFrag
import com.haichenyi.community.ui.frag.*
import com.haichenyi.mvvm.di.scope.FragScoped
import dagger.Module
import dagger.Provides

/**
 * @Author: 海晨忆
 * @Desc: fragment中其他变量的获取方式
 * @Date: 2020/1/3-17:26
 */

@Module
class FragMainModule {
  @Provides
  @FragScoped
  fun getFragments(fragment: FragMain): MutableList<Fragment> = mutableListOf(
    fragment.createFrag<FragHome>(),
    fragment.createFrag<FragSquare>(),
    fragment.createFrag<FragMsg>(),
    fragment.createFrag<FragAuthor>()
  )

  @Provides
  @FragScoped
  fun getItemsInt(): MutableList<Int> = mutableListOf(R.id.home, R.id.square, R.id.msg, R.id.author)
}