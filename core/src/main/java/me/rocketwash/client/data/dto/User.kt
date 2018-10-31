package me.rocketwash.client.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User constructor(
        id: Long,
        @SerializedName("fullName") val fullName: String? = null,
        @SerializedName("role") val position: String? = null,
        @SerializedName("email") val email: String? = null,
        @SerializedName("photo") val photo: String? = null
) : me.rocketwash.client.data.dto.Item(id), Serializable {
}