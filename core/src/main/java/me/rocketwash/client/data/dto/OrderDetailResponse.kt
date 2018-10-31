package me.rocketwash.client.data.dto

import me.rocketwash.client.data.responses.BaseResponse
import java.io.Serializable

class OrderDetailResponse(
        status: String?, data: me.rocketwash.client.data.dto.OrderDetail
) : BaseResponse<OrderDetail>(status, data), Serializable