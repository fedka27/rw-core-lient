package me.rocketwash.client.data.api.support

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import me.rocketwash.client.BuildConfig;
import me.rocketwash.client.data.dto.*
import me.rocketwash.client.data.dto.sign_in.LoginData
import me.rocketwash.client.data.responses.BaseResponse
import me.rocketwash.client.utils.log_d
import me.rocketwash.client.utils.tag
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiSupportImpl() {
    private var apiMapper = me.rocketwash.client.data.api.mapper.ApiMapper()
    private var requestsCall: MutableList<Call<*>> = mutableListOf()
    private var requestsJobs: MutableList<Job> = mutableListOf()

    companion object {
        private var apiRocketwash: me.rocketwash.client.data.api.support.ApiRocketwash? = null
        private var apiRocketWashCoroutines: me.rocketwash.client.data.api.ApiRocketWashCoroutines? = null
        private var apiMapper: me.rocketwash.client.data.api.mapper.ApiMapper? = null

        fun getInstanceApiSupport(): me.rocketwash.client.data.api.support.ApiRocketwash {
            if (apiRocketwash == null) {
                apiRocketwash = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                            .addInterceptor(
                                HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                            )
                            .build()
                    )
                    .build()
                    .create(me.rocketwash.client.data.api.support.ApiRocketwash::class.java)
            }
            return apiRocketwash!!
        }

        fun getInstanceApi(): me.rocketwash.client.data.api.ApiRocketWashCoroutines {
            if (apiRocketWashCoroutines == null) {
                apiRocketWashCoroutines = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                    .client(
                        OkHttpClient.Builder()
                            .addInterceptor(
                                HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                            )
                            .build()
                    )
                    .build()
                    .create(me.rocketwash.client.data.api.ApiRocketWashCoroutines::class.java)
            }
            return apiRocketWashCoroutines!!
        }

        @Deprecated("")
        fun getApiResponseMapperInstance(): me.rocketwash.client.data.api.mapper.ApiMapper {
            if (apiMapper == null) {
                apiMapper = me.rocketwash.client.data.api.mapper.ApiMapper()
            }
            return apiMapper!!
        }
    }

    fun signIn(
        phone: String, pincode: String,
        functionSuccess: (BaseResponse<LoginData>) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().signIn(phone, pincode)

        call.enqueue(object : Callback<BaseResponse<LoginData>> {
            override fun onFailure(call: Call<BaseResponse<LoginData>>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(call: Call<BaseResponse<LoginData>>, response: Response<BaseResponse<LoginData>>) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    fun getHistoryCompletable(
        sessionId: String,
        functionSuccess: (HistoryCompletableResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val job = GlobalScope.launch(Dispatchers.Main + CoroutineExceptionHandler { coroutineContext, throwable ->
            functionError.invoke(throwable)
        }) {
            val statsResponse = getInstanceApi().getHistoryStats(sessionId)
                .await().let { apiMapper.mapResponse(it) }.data

            val historyResponse = getInstanceApi().getHistory(sessionId, "completed")
                .await().let { apiMapper.mapResponse(it) }.data

            functionSuccess.invoke(
                HistoryCompletableResult(
                    statsResponse.total_count,
                    statsResponse.total_paid_with_bonuses,
                    statsResponse.total_discount_received,
                    historyResponse
                )
            )
        }

        requestsJobs.add(job)
    }

    /**
     * Create car
     * */
    fun createCar(
        sessionId: String,
        carMakeId: Int,
        carModelId: Int,
        year: Int,
        tag: String,
        functionSuccess: (BaseResponse<CarsAttributes>) -> Unit,
        functionError: (Throwable) -> Unit
    ) {

        val call = getInstanceApiSupport().createCar(sessionId, carMakeId, carModelId, year, tag)

        call.enqueue(object : Callback<BaseResponse<CarsAttributes>> {
            override fun onFailure(call: Call<BaseResponse<CarsAttributes>>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<BaseResponse<CarsAttributes>>,
                response: Response<BaseResponse<CarsAttributes>>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)

    }

    fun saveProfile(
        sessionId: String,
        profile: Profile,
        functionSuccess: (ProfileResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {

        val job = GlobalScope.launch(Dispatchers.Main + CoroutineExceptionHandler { coroutineContext, throwable ->
            functionError.invoke(throwable)
        }) {
            profile.cars_attributes?.forEach { carAttrs ->
                var response: Deferred<Response<BaseResponse<CarsAttributes>>>? = null

                if (carAttrs.id > 0 && carAttrs.type == 2) { //Delete car
                    log_d(tag(), "Delete car")
                    response = getInstanceApi().deleteCar(
                        sessionId,
                        carAttrs.id
                    )
                } else if (carAttrs.id > 0 && carAttrs.type == 0) { //Update
                    log_d(tag(), "Update car")
                    response = getInstanceApi().updateCar(
                        sessionId,
                        carAttrs.id,
                        carAttrs.car_make_id,
                        carAttrs.car_model_id,
                        carAttrs.year,
                        carAttrs.tag
                    )
                } else if (carAttrs.id == 0 && carAttrs.type == 0) { //Create car
                    log_d(tag(), "Create car")
                    response = getInstanceApi().addCar(
                        sessionId,
                        carAttrs.car_make_id.toString(),
                        carAttrs.car_model_id.toString(),
                        carAttrs.tag
                    )
                }

                response?.let {
                    val result = apiMapper.mapResponse(it.await())

                    log_d(tag(), "${carAttrs.type} ${result.status}")
                }
            }

            log_d(tag(), "saving profile name")
            saveUsername(sessionId, profile.name, functionSuccess, functionError)
        }

        requestsJobs.add(job)
    }

    fun saveUsername(
        sessionId: String,
        userName: String?,
        functionSuccess: (ProfileResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().putProfile(sessionId, userName)

        call.enqueue(object : Callback<ProfileResult> {
            override fun onFailure(call: Call<ProfileResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(call: Call<ProfileResult>, response: Response<ProfileResult>) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }

                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * get nearest washes
     * */
    fun getNearestWashes(
        sessionId: String,
        latitude: Double,
        longitude: Double,
        distance: Int,
        page: Int,
        functionSuccess: (me.rocketwash.client.data.dto.WashServiceResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getNearestWashes(sessionId, latitude, longitude, distance, page)
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.WashServiceResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.WashServiceResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.WashServiceResult>,
                response: Response<me.rocketwash.client.data.dto.WashServiceResult>
            ) {
                try {
                    val result = apiMapper.mapResponse(response)
                    functionSuccess.invoke(result)
                } catch (e: Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })
        requestsCall.add(call)
    }

    /**
     * Get services of wash by id
     * */
    fun getServices(
        sessionId: String,
        serviceId: Int,
        carModelId: Int,
        organizationId: Int,
        functionSuccess: (ChoiseServiceResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().services(sessionId, serviceId, carModelId, organizationId)

        call.enqueue(object : Callback<ChoiseServiceResult> {
            override fun onFailure(call: Call<ChoiseServiceResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(call: Call<ChoiseServiceResult>, response: Response<ChoiseServiceResult>) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * get reserved to washes
     * */
    fun getReservations(
        sessionId: String,
        functionSuccess: (me.rocketwash.client.data.dto.ReservedResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getReservedWashes(sessionId)
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ReservedResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ReservedResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ReservedResult>,
                response: Response<me.rocketwash.client.data.dto.ReservedResult>
            ) {
                try {
                    val result = apiMapper.mapResponse(response)
                    functionSuccess.invoke(result)
                } catch (e: Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })
        requestsCall.add(call)
    }

    /**
     * Cancel reserved
     * */
    fun cancelReserve(
        sessionId: String,
        washId: Int,
        organizationId: Int,
        functionSuccess: (me.rocketwash.client.data.dto.ReserveCancelResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().cancelReserve(sessionId, washId, organizationId)

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ReserveCancelResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ReserveCancelResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ReserveCancelResult>,
                response: Response<me.rocketwash.client.data.dto.ReserveCancelResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * Add Wash to favorite
     * */
    fun addFavorite(
        sessionId: String,
        washId: Int,
        organizationId: Int,
        functionSuccess: (me.rocketwash.client.data.dto.ProfileResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().addToFavorite(sessionId, washId, organizationId)
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ProfileResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ProfileResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ProfileResult>,
                response: Response<me.rocketwash.client.data.dto.ProfileResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }
        })
        requestsCall.add(call)
    }

    /**
     * Delete favorite
     * */
    fun deleteFavorite(
        sessionId: String,
        favoriteId: Int,
        functionSuccess: (me.rocketwash.client.data.dto.RemoveFavoriteResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().deleteFavorite(sessionId, favoriteId)
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.RemoveFavoriteResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.RemoveFavoriteResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.RemoveFavoriteResult>,
                response: Response<me.rocketwash.client.data.dto.RemoveFavoriteResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }
        })
        requestsCall.add(call)
    }

    /**
     * get favorites
     * */
    fun getFavorites(
        sessionId: String,
        functionSuccess: (me.rocketwash.client.data.dto.WashServiceResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getFavorites(sessionId)
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.WashServiceResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.WashServiceResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.WashServiceResult>,
                response: Response<me.rocketwash.client.data.dto.WashServiceResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }
        })
        requestsCall.add(call)
    }

    /**
     * get mobile info
     * */
    fun getMobileInfo(
        organizationId: Int? = null,
        functionSuccess: (me.rocketwash.client.data.dto.InfoResultData) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getMobileInfo(organizationId)
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.InfoResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.InfoResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.InfoResult>,
                response: Response<me.rocketwash.client.data.dto.InfoResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response).data)
                } catch (e: Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }
        })
        requestsCall.add(call)
    }

    /**
     * get available times to wash
     * */
    fun getAvailableTimes(
        sessionId: String,
        id: Int,
        organizationId: Int,
        timeRangeStart: String,
        timeRangeEnd: String,
        servicesDuration: Int,
        functionSuccess: (me.rocketwash.client.data.dto.AvailableTimesResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getAvailableTimes(
            sessionId, id,
            organizationId,
            timeRangeStart,
            timeRangeEnd,
            servicesDuration
        )
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.AvailableTimesResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.AvailableTimesResult>, t: Throwable) {
                functionError.invoke(t)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.AvailableTimesResult>,
                response: Response<me.rocketwash.client.data.dto.AvailableTimesResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
            }

        })
        requestsCall.add(call)
    }

    /**
     * get cars
     * */
    fun getCars(
        functionSuccess: (me.rocketwash.client.data.dto.CarsMakesResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {

        val call = getInstanceApiSupport().getCars()
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.CarsMakesResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.CarsMakesResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.CarsMakesResult>,
                response: Response<me.rocketwash.client.data.dto.CarsMakesResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })
        requestsCall.add(call)

    }

    /**
     * create empty user
     * */
    fun createEmptyUser(
        functionSuccess: (me.rocketwash.client.data.dto.EmptyUserResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {

        val call = getInstanceApiSupport().createEmptyUser()
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.EmptyUserResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.EmptyUserResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.EmptyUserResult>,
                response: Response<me.rocketwash.client.data.dto.EmptyUserResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })
        requestsCall.add(call)

    }

    /** get reservation detail*/
    fun getReservationDetails(
        sessionId: String,
        orderId: Int,
        organizationId: Int,
        functionSuccess: (me.rocketwash.client.data.dto.OrderDetail) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getReservationDetails(sessionId, orderId, organizationId)
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.OrderDetailResponse> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.OrderDetailResponse>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.OrderDetailResponse>,
                response: Response<me.rocketwash.client.data.dto.OrderDetailResponse>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response).data)
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })
        requestsCall.add(call)
    }

    /**
     * Request new pin to login
     * */
    fun requestNewPincode(
        phone: String,
        functionSuccess: (me.rocketwash.client.data.dto.PinResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().requestNewPin(phone.replace("+", ""))
        call.enqueue(object : Callback<me.rocketwash.client.data.dto.PinResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.PinResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.PinResult>,
                response: Response<me.rocketwash.client.data.dto.PinResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })
        requestsCall.add(call)
    }

    fun getMapDirection(
        originLatitude: Double,
        originLongitude: Double,
        destinationLatitude: Double,
        destinationLongitude: Double,
        functionSuccess: (me.rocketwash.client.data.dto.MapRouteResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().mapDirection(
            originLatitude,
            originLongitude,
            destinationLatitude,
            destinationLongitude
        )

        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val route = me.rocketwash.client.data.dto.MapRouteResult()
                try {
                    route.setStringData(response.body())
                    functionSuccess.invoke(route)
                } catch (e: Exception) {
                    e.printStackTrace()
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }
        })

        requestsCall.add(call)

    }

    fun answerOfQuestions(
        sessionId: String,
        reservationId: Int,
        organizationId: Int,
        scope: String,
        answers: List<me.rocketwash.client.data.dto.Answer>,
        functionSuccess: (me.rocketwash.client.data.dto.AnswersResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val reviewScaleIds = mutableMapOf<String, Int>()
        val ratings = mutableMapOf<String, Int>()
        val comments = mutableMapOf<String, String?>()

        for (answer in answers) {
            reviewScaleIds["ratings[][review_scale_id]"] = answer.review_scale_id
            ratings["ratings[][rating]"] = answer.rating
            comments["ratings[][comment]"] = answer.comment
        }

        val call = getInstanceApiSupport()
            .answerOfQuestions(
                sessionId,
                reservationId,
                organizationId,
                scope,
                reviewScaleIds,
                ratings,
                comments
            )

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.AnswersResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.AnswersResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.AnswersResult>,
                response: Response<me.rocketwash.client.data.dto.AnswersResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    fun reservation(
        sessionId: String,
        carwashId: Int,
        carId: Int,
        organizationId: Int,
        timeFrom: String,
        services: List<me.rocketwash.client.data.dto.ChoiceService>,
        functionSuccess: (me.rocketwash.client.data.dto.ReservationResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {

        val ids = mutableMapOf<String, Int>()
        val counts = mutableMapOf<String, Int>()

        services.filter { it.isCheck }
            .forEach {
                ids["services[][id]"] = it.id
                counts["services[][count]"] = 1 // todo move count to params
            }

        val call =
            getInstanceApiSupport().reservation(sessionId, carwashId, carId, organizationId, timeFrom, ids, counts)

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ReservationResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ReservationResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ReservationResult>,
                response: Response<me.rocketwash.client.data.dto.ReservationResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }


//    fun parse(jObject: JSONObject): List<List<HashMap<String, String>>> {
//
//        val routes = ArrayList<List<HashMap<String, String>>>()
//        var jRoutes: JSONArray? = null
//        var jLegs: JSONArray? = null
//        var jSteps: JSONArray? = null
//
//        try {
//
//            jRoutes = jObject.getJSONArray("routes")
//
//            /** Traversing all routes  */
//            for (i in 0 until jRoutes!!.length()) {
//                jLegs = (jRoutes.get(i) as JSONObject).getJSONArray("legs")
//                val path = ArrayList<HashMap<String, String>>()
//
//                /** Traversing all legs  */
//                for (j in 0 until jLegs!!.length()) {
//                    jSteps = (jLegs.get(j) as JSONObject).getJSONArray("steps")
//
//                    /** Traversing all steps  */
//                    for (k in 0 until jSteps!!.length()) {
//                        var polyline = ""
//                        polyline = ((jSteps.get(k) as JSONObject).get("polyline") as JSONObject).get("points") as String
//                        val list = decodePoly(polyline)
//
//                        /** Traversing all points  */
//                        for (l in list.indices) {
//                            val hm = HashMap<String, String>()
//                            hm["lat"] = java.lang.Double.toString(list[l].latitude)
//                            hm["lng"] = java.lang.Double.toString(list[l].longitude)
//                            path.add(hm)
//                        }
//                    }
//                    routes.add(path)
//                }
//            }
//
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return routes
//    }

//    private fun decodePoly(encoded: String): List<LatLng> {
//
//        val poly = ArrayList<LatLng>()
//        var index = 0
//        val len = encoded.length
//        var lat = 0
//        var lng = 0
//
//        while (index < len) {
//            var b: Int
//            var shift = 0
//            var result = 0
//            do {
//                b = encoded[index++].toInt() - 63
//                result = result or (b and 0x1f shl shift)
//                shift += 5
//            } while (b >= 0x20)
//            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
//            lat += dlat
//
//            shift = 0
//            result = 0
//            do {
//                b = encoded[index++].toInt() - 63
//                result = result or (b and 0x1f shl shift)
//                shift += 5
//            } while (b >= 0x20)
//            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
//            lng += dlng
//
//            val p = LatLng(lat.toDouble() / 1E5,
//                    lng.toDouble() / 1E5)
//            poly.add(p)
//        }
//
//        return poly
//    }

    /**
     * get profile
     * */
    fun getProfile(
        sessionId: String,
        functionSuccess: (me.rocketwash.client.data.dto.ProfileResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getProfile(sessionId)

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ProfileResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ProfileResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ProfileResult>,
                response: Response<me.rocketwash.client.data.dto.ProfileResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }

                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * get questions
     * */
    fun getQuestions(
        sessionId: String,
        functionSuccess: (me.rocketwash.client.data.dto.QuestionsResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().getQuestions(sessionId)

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.QuestionsResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.QuestionsResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.QuestionsResult>,
                response: Response<me.rocketwash.client.data.dto.QuestionsResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }

                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * Put reservation payment from tinkoff requiring
     * */
    fun putReservationPayment(
        sessionId: String,
        reservationId: Int,
        organizationId: Int,
        tinkoffTransactionId: Long,
        functionSuccess: (me.rocketwash.client.data.dto.ReservationPaymentResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().putReservationPayment(
            sessionId,
            reservationId,
            organizationId,
            tinkoffTransactionId
        )

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ReservationPaymentResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ReservationPaymentResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ReservationPaymentResult>,
                response: Response<me.rocketwash.client.data.dto.ReservationPaymentResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }

                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * Delete reservation by id
     * */
    fun cancelReservation(
        sessionId: String,
        reservationId: Int,
        organizationId: Int,
        functionSuccess: (me.rocketwash.client.data.dto.ReserveCancelResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().deleteReservation(sessionId, reservationId, organizationId)

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ReserveCancelResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ReserveCancelResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ReserveCancelResult>,
                response: Response<me.rocketwash.client.data.dto.ReserveCancelResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }
                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * set phone
     * */
    fun setPhone(
        sessionId: String,
        phone: String,
        functionSuccess: (me.rocketwash.client.data.dto.ProfileResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().setPhone(sessionId, phone)

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ProfileResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ProfileResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ProfileResult>,
                response: Response<me.rocketwash.client.data.dto.ProfileResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }

                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * verify phone
     * */
    fun verifyPhone(
        sessionId: String,
        pin: String,
        functionSuccess: (me.rocketwash.client.data.dto.ProfileResult) -> Unit,
        functionError: (Throwable) -> Unit
    ) {
        val call = getInstanceApiSupport().verifyPhone(sessionId, pin)

        call.enqueue(object : Callback<me.rocketwash.client.data.dto.ProfileResult> {
            override fun onFailure(call: Call<me.rocketwash.client.data.dto.ProfileResult>, t: Throwable) {
                functionError.invoke(t)
                requestsCall.remove(call)
            }

            override fun onResponse(
                call: Call<me.rocketwash.client.data.dto.ProfileResult>,
                response: Response<me.rocketwash.client.data.dto.ProfileResult>
            ) {
                try {
                    functionSuccess.invoke(apiMapper.mapResponse(response))
                } catch (e: java.lang.Exception) {
                    functionError.invoke(e)
                }

                requestsCall.remove(call)
            }

        })

        requestsCall.add(call)
    }

    /**
     * Cancel all requestsCall
     * */
    fun cancel() {
        requestsCall.forEach { it.cancel() }
        requestsJobs.filter { !it.isCancelled }.forEach { it.cancel() }
    }
}