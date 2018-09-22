package com.mindvalley.pinterest.model

import com.google.gson.Gson
import com.mindvalley.mvload.MVLoadClient
import com.mindvalley.mvload.core.mapper.StreamMapper
import com.mindvalley.pinterest.model.dto.PinterestItem
import javax.inject.Inject


class PinterestModelImpl @Inject constructor(private val clint: MVLoadClient) : PinterestModel {
    override fun getPinterestList(callback: (List<PinterestItem>?, Throwable?) -> Unit) {

        clint.requestAsGeneric("http://pastebin.com/raw/wgkJgazE",
                mapper = object : StreamMapper<ByteArray, List<PinterestItem>> {
                    override fun map(input: ByteArray): List<PinterestItem> {
                        return Gson().fromJson(String(input),
                                Array<PinterestItem>::class.java).toList()
                    }
                }, callback = callback)

    }


}