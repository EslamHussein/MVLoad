package com.mindvalley.pinterest.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PinterestItem(val id: String, val created_at: String, val width: Int,
                         val height: Int, val color: String, val likes: Int,
                         val liked_by_user: Boolean, val user: User, val urls: Urls):Parcelable

@Parcelize
data class User(val id: String, val username: String, val name: String, val profile_image: ProfileImages, val links: Links):Parcelable

@Parcelize
data class ProfileImages(val small: String, val medium: String, val large: String):Parcelable
@Parcelize
data class Links(val self: String, val html: String, val photos: String, val likes: String):Parcelable
@Parcelize
data class Urls(val raw: String, val full: String, val regular: String, val small: String, val thumb: String):Parcelable