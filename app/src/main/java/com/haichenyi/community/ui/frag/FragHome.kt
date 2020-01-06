package com.haichenyi.community.ui.frag

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.haichenyi.community.R
import com.haichenyi.community.base.BaseFrag
import com.haichenyi.community.databinding.FragHomeBinding
import com.haichenyi.community.vm.FragHomeVm

/**
 * @Author: 海晨忆
 * @Desc: 主页fragment
 * @Date: 2020/1/3-9:44
 */
class FragHome : BaseFrag<FragHomeBinding, FragHomeVm>(R.layout.frag_home) {
  override fun initView(binding: FragHomeBinding, bundle: Bundle?) {
    super.initView(binding, bundle)
  }
}