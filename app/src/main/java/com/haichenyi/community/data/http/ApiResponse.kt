package com.haichenyi.community.data.http

import retrofit2.HttpException
import retrofit2.Response
import java.util.regex.Pattern

sealed class ApiResponse<T> {

  companion object {
    fun <T> create(code: Int, error: Throwable): ApiResponse<T> {
      return ApiErrorResponse(code, error.message ?: "")
    }

    fun <T> create(response: Response<T>): ApiResponse<T> {
      val code = response.code()
      return if (response.isSuccessful) {
        val body = response.body()
        if (body == null || code == 204) {
          ApiErrorResponse(code, "content empty")
        } else {
          ApiSuccessResponse(body = body, linkHeader = response.headers()["link"])
        }
      } else {
        create(code, HttpException(response))
      }
    }
  }
}

data class ApiErrorResponse<T>(val code: Int, val msg: String) : ApiResponse<T>()

data class ApiSuccessResponse<T>(
  val body: T,
  val links: Map<String, String>
) : ApiResponse<T>() {
  constructor(body: T, linkHeader: String?) : this(
    body = body,
    links = linkHeader?.extractLinks() ?: emptyMap()
  )

  companion object {
    private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")

    private fun String.extractLinks(): Map<String, String> {
      val links = mutableMapOf<String, String>()
      val matcher = LINK_PATTERN.matcher(this)

      while (matcher.find()) {
        val count = matcher.groupCount()
        if (count == 2) {
          links[matcher.group(2) ?: ""] = matcher.group(1) ?: ""
        }
      }
      return links
    }
  }
}