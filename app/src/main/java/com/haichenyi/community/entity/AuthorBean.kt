package com.haichenyi.community.entity

data class AuthorBean(
  var name: String,
  var desc: String,
  var time: String,
  var pic: String,
  var title: String,
  var address: String,
  var categories: List<Categories>
) {
  data class Categories(var name: String, var address: String)
}


