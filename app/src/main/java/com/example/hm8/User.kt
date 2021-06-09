package com.example.hm8

import com.google.gson.annotations.SerializedName

data class User(

    val id: Long?,

    val email: String?,
    val avatar: String?,

    @SerializedName("first_name")
    val firstName: String?,

    @SerializedName("last_name")
    val lastName: String?,



)

data class ReqresData<T>(

    val page: Int?,

    val data: T?,

    val support : UserHelper?

)


data class UserHelper(
    val text : String?
)


