package me.rocketwash.client.data.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseResponse<T>(
        @SerializedName("status") val status: String? = null,
        @SerializedName("data") val data: T
) : Serializable {
    fun isSuccess() = status.equals("success")
}