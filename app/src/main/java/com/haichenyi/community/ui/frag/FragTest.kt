package com.haichenyi.community.ui.frag

import android.os.Bundle
import com.haichenyi.community.R
import com.haichenyi.community.base.BaseFrag
import com.haichenyi.community.databinding.FragTestBinding
import com.haichenyi.community.vm.FragTestVm

/**
 * @Author: 海晨忆
 * @Desc: 测试fragment
 * @Date: 2020/1/6-15:23
 */
class FragTest : BaseFrag<FragTestBinding, FragTestVm>(R.layout.frag_test) {
  override fun initView(binding: FragTestBinding, bundle: Bundle?) {
    super.initView(binding, bundle)
  }
}