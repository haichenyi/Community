package com.haichenyi.community.ui.act

import android.os.Bundle
import com.haichenyi.community.R
import com.haichenyi.community.base.BaseAct
import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.data.http.httpObserver
import com.haichenyi.community.databinding.ActMainBinding

class MainAct : BaseAct<ActMainBinding>(R.layout.act_main) {
  override fun initDataBind(actMainBinding: ActMainBinding, savedInstanceState: Bundle?) {
    super.initDataBind(actMainBinding, savedInstanceState)

    var dataCompat = BaseApp.baseApp.appComponent.dataCompat()
    dataCompat.getAuthor().observe(this, httpObserver {
      onSuccess { }
      onFailed { i, s -> }
    })
  }
}
