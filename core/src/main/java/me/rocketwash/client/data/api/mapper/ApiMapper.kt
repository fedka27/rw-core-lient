package me.rocketwash.client.data.api.mapper

import com.google.gson.Gson
import me.rocketwash.client.data.exceptions.ApiResponseException
import me.rocketwash.client.data.exceptions.UnauthorizedException
import me.rocketwash.client.data.responses.BaseResponse
import me.rocketwash.client.utils.log
import retrofit2.Response

class ApiMapper {

    @Throws(Exception::class)
    fun <T : BaseResponse<*>> mapResponse(response: Response<T>): T {

        val url = response.raw().request().url().toString()


        log("url: $url")

        //todo parse response fix
        val baseResponse = response.body()

        if (baseResponse != null) {
            if (baseResponse.isSuccess()) return baseResponse

            if (baseResponse.data is me.rocketwash.client.data.dto.base.BaseData) {
                throw ApiResponseException(baseResponse.data.result)
            }
        }

        if (response.code() == 401) {
            throw UnauthorizedException()
        }
        val json = response.errorBody()?.string()

        val baseResponseDto: BaseResponse<*>? = json?.let {
            Gson().fromJson(it, BaseResponse::class.java)
        }

        val baseData: me.rocketwash.client.data.dto.base.BaseData = if (baseResponseDto != null && baseResponse!!.data != null)
            baseResponse.data as me.rocketwash.client.data.dto.base.BaseData
        else
            me.rocketwash.client.data.dto.base.BaseData("${response.code()} ${response.raw().message()}")

        val message = baseData.result

        throw ApiResponseException(message)

    }

//    private fun parseApiException(message: String?): String {
//        return when (message) {
//            "not_found" -> appProvider.getString(R.string.error_not_found)
//            "wrong_pin" -> appProvider.getString(R.string.error_wrong_pin)
//            "Pin is not valid" -> appProvider.getString(R.string.error_wrong_pin)
//
//            //todo add other exception
//            else -> message ?: appProvider.getString(R.string.error_unknown)
//        }
//    }
}