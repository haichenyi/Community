package com.haichenyi.community.data.http


object HttpProtocol {
  //http://127.0.0.1:8080/examples/info.json
  const val HTTP_HOST = "https://www.haichenyi.cn"
  private const val HTTP_PORT = ":8888"
  /**
   * 获取用户列表
   */
  const val HTTP_GET_USERS = "$HTTP_HOST$HTTP_PORT/getUsers"
  /**
   * 获取用户详细信息
   */
  const val HTTP_GET_USER_BY_ID = "$HTTP_HOST$HTTP_PORT/getUserById/"

  /**
   * 获取省市区信息
   */
  const val HTTP_PROVIENCES = "$HTTP_HOST$HTTP_PORT/getProvinceInfos"

  /**
   * 获取视频列表
   */
  const val HTTP_GET_VIDEOS = "$HTTP_HOST$HTTP_PORT/getVideoLists"

  /**
   * 上传视频
   */
  const val HTTP_UPLOAD_VIDEO = "$HTTP_HOST$HTTP_PORT/upload/uploadVideo"

  /**
   * 获取作者信息
   */
  const val HTTP_GET_AUTHOR = "$HTTP_HOST$HTTP_PORT/getAuthor"


}