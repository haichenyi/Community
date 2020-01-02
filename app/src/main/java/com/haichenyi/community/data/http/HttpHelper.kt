package com.haichenyi.community.data.http

import androidx.lifecycle.LiveData
import com.haichenyi.community.entity.AuthorBean
import com.haichenyi.community.entity.BaseData
import com.haichenyi.community.entity.UserBean
import com.haichenyi.community.entity.VideoBean
import retrofit2.http.Path

interface HttpHelper {
  fun getUsers(): LiveData<ApiResponse<BaseData<List<UserBean>>>>

  fun getUserById(@Path("userId") userId: String): LiveData<ApiResponse<BaseData<UserBean>>>

  fun getVideos(): LiveData<ApiResponse<BaseData<List<VideoBean>>>>

  fun getAuthor(): LiveData<ApiResponse<BaseData<AuthorBean>>>
}