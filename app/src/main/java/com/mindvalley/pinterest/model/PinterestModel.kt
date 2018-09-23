package com.mindvalley.pinterest.model

import com.mindvalley.pinterest.model.dto.PinterestItem

interface PinterestModel {

    fun getPinterestList(callback: (List<PinterestItem>?, Throwable?) -> Unit)
}