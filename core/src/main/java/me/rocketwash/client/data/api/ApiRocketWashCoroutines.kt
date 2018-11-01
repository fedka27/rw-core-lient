package me.rocketwash.client.data.api

import kotlinx.coroutines.experimental.Deferred
import me.rocketwash.client.data.dto.CarsAttributes
import me.rocketwash.client.data.dto.sign_in.LoginData
import me.rocketwash.client.data.responses.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

//todo add other api method
interface ApiRocketWashCoroutines {
    companion object {
        const val HEADER_SESSION = "X-Rocketwash-Session-Id"
    }


    /**
     * Sign in user
     * @param phone user's phone number
     * @param pin user's pin code from the SMS
     * */
    @POST("session/sign_in")
    fun signIn(@Query("phone") phone: String,
               @Query("pin") pin: String
    ): Deferred<Response<BaseResponse<LoginData>>>

    @POST("session/sign_in")
    fun signInAsync(@Query("phone") phone: String,
                    @Query("pin") pin: String
    ): Call<Response<BaseResponse<me.rocketwash.client.data.dto.sign_in.LoginData>>>

    /**
     * Register device for the push notification
     * @param sessionId user's session id
     * @param firebaseToken firebase device token
     * @param userToken user's session id
     * */
    @PUT("one_signal/regitster_device")
    //todo change to onesignal register
    fun registerDevice(@Header(HEADER_SESSION) sessionId: String,
                       @Query("appId") appId: String,
                       @Query("playerId") playerId: String,
                       @Query("organizationId") userToken: String
    ): Deferred<Response<BaseResponse<me.rocketwash.client.data.dto.base.BaseData>>>

    /**
     * Request a new pin code to login of the phone number
     * @param phone user's phone number must be without + character
     * */
    @POST("user_actions/request_pin")
    fun resetPinCode(@Query("phone") phone: String): Deferred<Response<ResetPinCodeResponse>>

    /**
     * Request a new pin code to register of the phone number
     * @param phone user's phone number must be without + character
     * */
    @POST("user_actions/set_phone")
    fun setPhone(@Header(HEADER_SESSION) session: String,
                 @Query("phone") phone: String): Deferred<Response<RegisterPhoneResponse>>

    /**
     * Verify phone number after register
     * @param phone pin code from the SMS
     * */
    @POST("user_actions/verify_phone")
    fun verifyPhone(@Header(HEADER_SESSION) session: String,
                    @Query("pin") phone: String): Deferred<Response<VerifyPhoneResponse>>


    /**
     * Create empty user
     * */
    @POST("user_actions/create_empty_user")
    fun createEmptyUser(): Deferred<Response<EmptyUserResponse>>

    /**
     * Add user's car
     * */
    @POST("cars")
    fun addCar(@Header(HEADER_SESSION) session: String,
               @Query("car_make_id") brandId: String,
               @Query("car_model_id") modelId: String,
               @Query("tag") carNumber: String?
    ): Deferred<Response<BaseResponse<me.rocketwash.client.data.dto.CarsAttributes>>>


    @PUT("cars/id")
    fun updateCar(
        @Header(HEADER_SESSION) sessionId: String,
        @Query("id") carId: Int,
        @Query("car_make_id") carMakeId: Int,
        @Query("car_model_id") carModelId: Int,
        @Query("year") year: Int,
        @Query("tag") tag: String?
    ): Deferred<Response<BaseResponse<CarsAttributes>>>

    @DELETE("cars/id")
    fun deleteCar(
        @Header(HEADER_SESSION) sessionId: String,
        @Query("id") carId: Int
    ): Deferred<Response<BaseResponse<CarsAttributes>>>

}