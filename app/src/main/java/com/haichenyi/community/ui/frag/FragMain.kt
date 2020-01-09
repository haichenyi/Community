package com.haichenyi.community.ui.frag

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.haichenyi.community.R
import com.haichenyi.community.base.BaseFrag
import com.haichenyi.community.common.createFrag
import com.haichenyi.community.common.showToast
import com.haichenyi.community.databinding.FragMainBinding
import com.haichenyi.community.databinding.FragTestBinding
import com.haichenyi.community.ui.adapter.FragmentAdapter
import com.haichenyi.community.vm.FragMainVm
import javax.inject.Inject

/**
 * @Author: 海晨忆
 * @Desc: 主页面：底部导航栏+ViewPager2
 * @Date: 2020/1/3-14:18
 */
class FragMain : BaseFrag<FragMainBinding, FragMainVm>(R.layout.frag_main) {
  @Inject
  lateinit var fragments: MutableList<Fragment>

  @Inject
  lateinit var itemInts: MutableList<Int>

  private lateinit var viewPager2: ViewPager2

  var i = 0

  override fun initView(binding: FragMainBinding, bundle: Bundle?) {
    super.initView(binding, bundle)
    viewPager2 = binding.vp2.also {
      it.adapter = FragmentAdapter(this, fragments)
      it.isUserInputEnabled = false
      it.offscreenPageLimit = fragments.size - 1
    }

    binding.bottomNavigation.setOnNavigationItemSelectedListener {

      viewPager2.setCurrentItem(itemInts.indexOf(it.itemId), false)
      true
    }
    binding.setListener {
      when (it.id) {
        R.id.fab -> {
          findNavController().navigate(FragMainDirections.mainToVideoUpload())
        }
      }
    }
  }

  override fun onResume() {
    super.onResume()
    if (viewPager2.adapter == null) {
      viewPager2.adapter = FragmentAdapter(this, fragments)
    }
  }

  override fun onDestroyView() {
    viewPager2.adapter = null
    super.onDestroyView()
  }
}