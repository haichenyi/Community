package com.haichenyi.community.data.http

import androidx.lifecycle.LiveData
import com.haichenyi.community.entity.AuthorBean
import com.haichenyi.community.entity.BaseData
import com.haichenyi.community.entity.UserBean
import com.haichenyi.community.entity.VideoBean
import javax.inject.Inject

class HttpImpl @Inject constructor(private val httpApi: HttpApi) : HttpHelper {
  override fun getUsers(): LiveData<ApiResponse<BaseData<List<UserBean>>>> = httpApi.getUsers()

  override fun getUserById(userId: String): LiveData<ApiResponse<BaseData<UserBean>>> =
    httpApi.getUserById(userId)

  override fun getVideos(): LiveData<ApiResponse<BaseData<List<VideoBean>>>> = httpApi.getVideos()

  override fun getAuthor(): LiveData<ApiResponse<BaseData<AuthorBean>>> = httpApi.getAuthor()
}