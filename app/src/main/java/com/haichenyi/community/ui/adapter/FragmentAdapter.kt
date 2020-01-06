package com.haichenyi.community.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author: 海晨忆
 * @Desc: ViewPager2的FragmentAdapter
 * @Date: 2020/1/3-17:13
 */
class FragmentAdapter(fragment: Fragment, private val data: MutableList<out Fragment>) :
  FragmentStateAdapter(fragment) {
  override fun getItemCount(): Int = data.size

  override fun createFragment(position: Int): Fragment = data[position]

}