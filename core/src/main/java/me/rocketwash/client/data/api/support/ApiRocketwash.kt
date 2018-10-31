package me.rocketwash.client.data.api.support

import me.rocketwash.client.data.api.ApiRocketWashCoroutines.Companion.HEADER_SESSION
import me.rocketwash.client.data.dto.sign_in.LoginData
import me.rocketwash.client.data.dto.sign_in.SignIn
import me.rocketwash.client.data.responses.BaseResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiRocketwash {
    /**
     * Sign in user
     * @param signIn data to sign in
     * */
    @Deprecated("old method. Use coroutines")
    @POST("session/sign_in")
    fun signIn(signIn: SignIn,
               success: (me.rocketwash.client.data.dto.sign_in.LoginData) -> Unit,
               exception: (Exception) -> Unit
    ): Call<Response<BaseResponse<LoginData>>>

    /**
     * Nearest Washes
     * */
    @GET("service_locations/nearest")
    fun getNearestWashes(@Header(me.rocketwash.client.data.api.ApiRocketWashCoroutines.HEADER_SESSION) sessionId: String,
                         @Query("latitude") latitude: Double,
                         @Query("longitude") longitude: Double,
                         @Query("distance") distance: Int,
                         @Query("page") page: Int
    ): Call<me.rocketwash.client.data.dto.WashServiceResult>

    /**
     * Reserved Washes
     * */
    @GET("reservations")
    fun getReservedWashes(@Header(me.rocketwash.client.data.api.ApiRocketWashCoroutines.HEADER_SESSION) sessionId: String): Call<me.rocketwash.client.data.dto.ReservedResult>

    /**
     * Add to favorite
     * */
    @POST("favourites")
    fun addToFavorite(@Header(me.rocketwash.client.data.api.ApiRocketWashCoroutines.HEADER_SESSION) sessionId: String,
                      @Query("id") washId: Int,
                      @Query("organization_id") organizationId: Int
    ): Call<me.rocketwash.client.data.dto.ProfileResult>

    /**
     * Remove favorite
     * */
    @DELETE("favourites/{favorite_id}")
    fun deleteFavorite(@Header(me.rocketwash.client.data.api.ApiRocketWashCoroutines.HEADER_SESSION) sessionId: String,
                       @Path("favorite_id") favoriteId: Int
    ): Call<me.rocketwash.client.data.dto.RemoveFavoriteResult>

    /**
     * Cancel reserve
     * */
    @POST("reservations/{id}")
    fun cancelReserve(@Header(me.rocketwash.client.data.api.ApiRocketWashCoroutines.HEADER_SESSION) sessionId: String,
                      @Path("id") washId: Int,
                      @Query("organization_id") organizationId: Int
    ): Call<me.rocketwash.client.data.dto.ReserveCancelResult>

    /**
     * Available times of wash service
     * */
    @GET("service_locations/{id}/available_times")
    fun getAvailableTimes(@Header(HEADER_SESSION) sessionId: String,
                          @Path("id") id: Int,
                          @Query("organization_id") organizationId: Int,
                          @Query("time_range_start") timeRangeStart: String,
                          @Query("time_range_end") timeRangeEnd: String,
                          @Query("services_duration") servicesDuration: Int
    ): Call<me.rocketwash.client.data.dto.AvailableTimesResult>

    /**
     * Cars
     * */
    @GET("car_makes")
    fun getCars(): Call<me.rocketwash.client.data.dto.CarsMakesResult>


    /**
     * Services
     * */
    @GET("services")
    fun services(@Header(HEADER_SESSION) sessionId: String,
                 @Query("service_location_id") serviceId: Int,
                 @Query("car_model_id") carModelId: Int,
                 @Query("organization_id") organizationId: Int
    ): Call<me.rocketwash.client.data.dto.ChoiseServiceResult>

    /**
     * Create car
     * */
    @POST("cars")
    fun createCar(@Header(HEADER_SESSION) sessionId: String,
                  @Query("car_make_id") carMakeId: Int,
                  @Query("car_model_id") carModelId: Int,
                  @Query("tag") tag: String
    ): Call<me.rocketwash.client.data.dto.PostCarResult>

    /**
     * Create empty user
     * */
    @POST("user_actions/create_empty_user")
    fun createEmptyUser(): Call<me.rocketwash.client.data.dto.EmptyUserResult>

    /**
     * Get favorites washes
     * */
    @GET("favourites")
    fun getFavorites(@Header(HEADER_SESSION) sessionId: String): Call<me.rocketwash.client.data.dto.WashServiceResult>

    @GET("organizations/mobile_info")
    //todo check
    fun getMobileInfo(@Query("organization_id") organizationId: Int? = null): Call<me.rocketwash.client.data.dto.InfoResult>

    @GET("reservations/{id}")
    fun getReservationDetails(@Header(HEADER_SESSION) sessionId: String,
                              @Query("organization_id") organizationId: Int
    ): Call<me.rocketwash.client.data.dto.OrderDetailResponse>

    /**
     * Request to new pin
     * @param phone must be without +
     * */
    @POST("user_actions/request_pin")
    fun requestNewPin(@Query("phone") phone: String): Call<me.rocketwash.client.data.dto.PinResult>

    @GET("https://maps.googleapis.com/maps/api/directions/json?" +
            "origin={origin_latitude},{origin_longitude}" +
            "&" +
            "destination={destination_latitude},{destination_longitude}" +
            "&" +
            "sensor=false")
    //todo change to actual model
    fun mapDirection(@Path("origin_latitude") originLatitude: Double,
                     @Path("origin_longitude") originLongitude: Double,
                     @Path("destination_longitude") destinationLatitude: Double,
                     @Path("destination_longitude") destinationLongitude: Double
    ): Call<String>

    /**
     * Answer of questions
     * @param scope aggregator or individual
     * */
    @POST("reviews")
    fun answerOfQuestions(@Header(HEADER_SESSION) sessionId: String,
                          @Query("reservation_id") reservationId: Int,
                          @Query("organization_id") organizationId: Int,
                          @Query("scope") scope: String,
                          @QueryMap reviewScaleIds: Map<String, Int>,
                          @QueryMap ratings: Map<String, Int>,
                          @QueryMap comments: Map<String, String?>
    ): Call<me.rocketwash.client.data.dto.AnswersResult>


    /** get profile */
    @GET("profile")
    fun getProfile(@Header(HEADER_SESSION) sessionId: String): Call<me.rocketwash.client.data.dto.ProfileResult>

    /**
     * get questions
     * */
    @GET("review_scales")
    fun getQuestions(@Header(HEADER_SESSION) sessionId: String): Call<me.rocketwash.client.data.dto.QuestionsResult>


    @POST("reservations")
    fun reservation(@Header(HEADER_SESSION) sessionId: String,
                    @Query("carwash_id") carwashId: Int,
                    @Query("car_id") carId: Int,
                    @Query("organization_id") organizationId: Int,
                    @Query("time_from") timeFrom: String,
                    @QueryMap ids: Map<String, Int>,
                    @QueryMap counts: Map<String, Int>
    ): Call<me.rocketwash.client.data.dto.ReservationResult>

    /**
     * Put reservation payment
     * */
    @PUT("reservations/{reservation_id}")
    fun putReservationPayment(@Header(HEADER_SESSION) sessionId: String,
                              @Path("reservation_id") reservationId: Int,
                              @Query("organization_id") organizationId: Int,
                              @Query("tinkoff_transaction_id") tinkoffTransactionId: Int
    ): Call<me.rocketwash.client.data.dto.ReservationPaymentResult>

    /**
     * Delete reservation by id
     * */
    @DELETE("reservations/{reservation_id}")
    fun deleteReservation(@Header(HEADER_SESSION) sessionId: String,
                          @Path("reservation_id") reservationId: Int,
                          @Query("organization_id") organizationId: Int
    ): Call<me.rocketwash.client.data.dto.ReserveCancelResult>

    /**
     * Set phone
     * */
    @POST("user_actions/set_phone")
    fun setPhone(@Header(HEADER_SESSION) sessionId: String,
                 @Query("phone") phone: String): Call<me.rocketwash.client.data.dto.ProfileResult>

    /**
     * Verify phone
     * */
    @POST("user_actions/verify_phone")
    fun verifyPhone(@Header(HEADER_SESSION) sessionId: String,
                    @Query("pin") pin: String): Call<me.rocketwash.client.data.dto.ProfileResult>

    //todo set other methods from spice manager

}