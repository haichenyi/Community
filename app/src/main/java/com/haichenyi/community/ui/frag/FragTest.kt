package com.haichenyi.community.ui.frag

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.haichenyi.community.R
import com.haichenyi.community.base.BaseFrag
import com.haichenyi.community.common.createFrag
import com.haichenyi.community.databinding.FragTestBinding
import com.haichenyi.community.ui.adapter.FragmentAdapter
import com.haichenyi.community.vm.FragTestVm

/**
 * @Author: 海晨忆
 * @Desc: 测试fragment
 * @Date: 2020/1/6-15:23
 */
class FragTest : BaseFrag<FragTestBinding, FragTestVm>(R.layout.frag_test) {
  lateinit var viewPager2: ViewPager2
  var i = 0
  override fun initView(binding: FragTestBinding, bundle: Bundle?) {
    super.initView(binding, bundle)
    val fragments = mutableListOf<Fragment>(
      createFrag<FragHome>(),
      createFrag<FragSquare>(),
      createFrag<FragMsg>(),
      createFrag<FragAuthor>()
    )
    viewPager2 = binding.vp2.also {
      it.adapter = FragmentAdapter(this@FragTest, fragments)
      it.isUserInputEnabled = false
      it.offscreenPageLimit = fragments.size - 1
    }
    binding.btn.setOnClickListener {
      viewPager2.setCurrentItem(++i % 4, false)
    }
  }
}