package com.mindvalley.mvload.core

data class BaseResponse<Data>(val code: Int, val exception: Exception? = null,
                              val data: Data? = null)
