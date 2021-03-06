package com.haichenyi.community.vm

import androidx.fragment.app.Fragment
import com.haichenyi.community.base.BaseViewModel
import javax.inject.Inject

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/3-15:30
 */
class FragMainVm @Inject constructor() : BaseViewModel() {
  @Inject
  lateinit var fragments: MutableList<Fragment>

  @Inject
  lateinit var itemInts: MutableList<Int>

}