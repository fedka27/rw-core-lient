package me.rocketwash.client.data.dto

import me.rocketwash.client.utils.dateTimePattern
import me.rocketwash.client.utils.toDateWithFormat
import java.util.*

class Note(
        val id: Long = 0,
        var title: String,
        var description: String,
        val createdAt: String,
        var updatedAt: String?
) {

    fun isUpdated(): Boolean = updatedAt != null

    fun getUpdatedDateTime(): Date? = updatedAt?.toDateWithFormat(dateTimePattern)

    fun getCreatedDateTime(): Date? = createdAt.toDateWithFormat(dateTimePattern)

}