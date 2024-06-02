package com.example.minichat.http

enum class RequestMethod {
  GET("GET"),
  POST("POST"),
  PUT("PUT"),
  PATH("PATH"),
  DELETE("DELETE");

  val method: String

  constructor(method: String) {
    this.method = method
  }
  override fun toString(): String {
    return method
  }
}