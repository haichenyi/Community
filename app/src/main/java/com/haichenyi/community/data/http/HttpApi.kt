package com.haichenyi.community.data.http

import androidx.lifecycle.LiveData
import com.haichenyi.community.entity.AuthorBean
import com.haichenyi.community.entity.BaseData
import com.haichenyi.community.entity.UserBean
import com.haichenyi.community.entity.VideoBean
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HttpApi {
  @GET(HttpProtocol.HTTP_GET_USERS)
  fun getUsers(): LiveData<ApiResponse<BaseData<List<UserBean>>>>

  @FormUrlEncoded
  @GET(HttpProtocol.HTTP_GET_USER_BY_ID + "{userId}")
  fun getUserById(@Path("userId") userId: String): LiveData<ApiResponse<BaseData<UserBean>>>

  @GET(HttpProtocol.HTTP_GET_VIDEOS)
  fun getVideos(): LiveData<ApiResponse<BaseData<List<VideoBean>>>>

  @GET(HttpProtocol.HTTP_GET_AUTHOR)
  fun getAuthor(): LiveData<ApiResponse<BaseData<AuthorBean>>>
}