package com.mindvalley.mvload.mapper

interface StreamMapper<in InputSteam, out Result> {

    fun map(inputSteam: InputSteam): Result
}