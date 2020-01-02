package com.haichenyi.community.data.http

import androidx.lifecycle.Observer
import com.haichenyi.community.entity.BaseData

class HttpObserver<T> : Observer<ApiResponse<BaseData<T>>> {
  private var onSuccess: ((T) -> Unit)? = null
  private var onFailed: ((Int, String) -> Unit)? = null
  private var onCompleted: (() -> Unit)? = null

  fun onSuccess(onSuccess: ((T) -> Unit)) {
    this.onSuccess = onSuccess
  }

  fun onFailed(onFailed: ((Int, String) -> Unit)) {
    this.onFailed = onFailed
  }

  fun onCompleted(onCompleted: (() -> Unit)) {
    this.onCompleted = onCompleted
  }

  override fun onChanged(t: ApiResponse<BaseData<T>>?) {
    when (t) {
      is ApiSuccessResponse<BaseData<T>> -> {
        val data = t.body.data
        if (null == data) {
          onFailed?.invoke(999, "result data is null")
        } else {
          onSuccess?.invoke(data)
        }
      }
      is ApiErrorResponse<BaseData<T>> -> onFailed?.invoke(t.code, t.msg)
    }
    onCompleted?.invoke()
  }
}

fun <T> httpObserver(block: HttpObserver<T>.() -> Unit): HttpObserver<T> =
  HttpObserver<T>().apply(block)
