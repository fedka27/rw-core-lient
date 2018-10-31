package me.rocketwash.client.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Item(
        @SerializedName("id") @Expose val id: Long
) : Serializable {


    override fun equals(other: Any?): Boolean {
        if (other is Item) return id == other.id

        return super.equals(other)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}