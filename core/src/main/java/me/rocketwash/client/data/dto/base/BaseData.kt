package me.rocketwash.client.data.dto.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseData(
        @SerializedName("result") var result: String? = null
) : Serializable