package com.haichenyi.community.entity

data class VideoBean(
  var videoId: Int,
  var videoType: String,
  var videoSize: String,
  var videoOldName: String,
  var videoNewName: String,
  var videoPath: String
)