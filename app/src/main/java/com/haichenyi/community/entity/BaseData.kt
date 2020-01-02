package com.haichenyi.community.entity

data class BaseData<T>(val code: Int, val msg: String, val data: T)