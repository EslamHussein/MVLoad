package com.mindvalley.pinterest.model

import com.mindvalley.mvload.core.mapper.StreamMapper
import com.mindvalley.pinterest.model.dto.PinterestItem
import javax.inject.Inject
import com.google.gson.Gson
import com.mindvalley.mvload.MVLoadClient
import com.mindvalley.mvload.core.Method


class PinterestModelImpl @Inject constructor(private val clint: MVLoadClient) : PinterestModel {
    override fun getPinterestList(callback: (List<PinterestItem>?, Throwable?) -> Unit) {

        clint.loadAsJsonObject("clint",method = Method.POST) { result, error ->


        }

        clint.loadAsGeneric("http://pastebin.com/raw/wgkJgazE",
                mapper = object : StreamMapper<ByteArray, List<PinterestItem>> {
                    override fun map(inputStream: ByteArray): List<PinterestItem> {
                        return Gson().fromJson(String(inputStream),
                                Array<PinterestItem>::class.java).toList()
                    }
                }, callback = callback)

    }


}