package com.haichenyi.community.ui.act

import android.os.Bundle
import androidx.navigation.findNavController
import com.haichenyi.community.R
import com.haichenyi.community.base.BaseAct
import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.data.http.httpObserver
import com.haichenyi.community.databinding.ActMainBinding

class MainAct : BaseAct<ActMainBinding>(R.layout.act_main) {

  override fun initDataBind(binding: ActMainBinding, savedInstanceState: Bundle?) {
    super.initDataBind(binding, savedInstanceState)
  }

  //onSupportNavigateUp()方法的重写，意味着Activity将它的 back键点击事件的委托出去，
  // 如果当前并非栈中顶部的Fragment, 那么点击back键，返回上一个Fragment。
  override fun onSupportNavigateUp() = findNavController(R.id.nav_graph_main).navigateUp()
}
