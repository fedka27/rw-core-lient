package me.rocketwash.client.data.repository.user

import me.rocketwash.client.data.dto.sign_in.SignIn
import me.rocketwash.client.data.exceptions.UnauthorizedException
import me.rocketwash.client.providers.AppProvider
import me.rocketwash.client.utils.isOnlineOrThrow
import me.rocketwash.client.utils.log_e
import me.rocketwash.client.utils.log_i

class UserRepositoryImpl(
        private val apiRocketWashCoroutines: me.rocketwash.client.data.api.ApiRocketWashCoroutines,
        private val apiMapper: me.rocketwash.client.data.api.mapper.ApiMapper,
        private val appProvider: AppProvider
) : UserRepository {

    override suspend fun signIn(signIn: SignIn): me.rocketwash.client.data.dto.sign_in.LoginData {
        log_i("apiRocketWashCoroutines sign_in")
        appProvider.isOnlineOrThrow()

        val response = apiMapper.mapResponse(apiRocketWashCoroutines
                .signIn(signIn.phone, signIn.pinCode)
                .await())


        return response.data
    }

    override suspend fun tryRegisterDevice(sessionId: String,
                                           playerId: String) {
        log_i("apiRocketWashCoroutines register device")
        appProvider.isOnlineOrThrow()

        try {
            //todo register device

//            apiMapper.mapResponse(apiRocketWashCoroutines
//                    .registerDevice(sessionId, firebaseToken, sessionId)
//                    .await())
//            log_d("Device registered successful")
        } catch (e: Exception) {
            e.printStackTrace()
            log_e("Device registered failed")
        }
    }

    override suspend fun sendPinCode(phone: String): Boolean {
        log_i("apiRocketWashCoroutines reset pin code")
        appProvider.isOnlineOrThrow()

        return apiMapper.mapResponse(apiRocketWashCoroutines
                .resetPinCode(phone.replace("+", ""))
                .await())
                .isSuccess()
    }

    override suspend fun createEmptyUser(): me.rocketwash.client.data.dto.Profile {

        return apiMapper.mapResponse(apiRocketWashCoroutines
                .createEmptyUser()
                .await())
                .data
    }

    override suspend fun changePhone(sesstionId: String, phone: String): me.rocketwash.client.data.dto.Profile {

        return apiMapper.mapResponse(apiRocketWashCoroutines
                .setPhone(sesstionId, phone.replace("+", ""))
                .await())
                .data
    }

    override suspend fun registerUser(phone: String): me.rocketwash.client.data.dto.Profile {
        log_i("register user")
        var profile = createEmptyUser()

        profile = apiMapper.mapResponse(apiRocketWashCoroutines
                .setPhone(profile.session_id, phone)
                .await())
                .data

        //todo must be set phone from the server
        profile.phone = phone

        return profile
    }

    override suspend fun activateProfile(profile: me.rocketwash.client.data.dto.Profile, pinCode: String): Boolean {

        return apiMapper.mapResponse(apiRocketWashCoroutines
                .signIn(profile.phone, pinCode)
                //todo verify phone
                //.verifyPhone(profile.session_id, pinCode)
                .await())
                .isSuccess()
    }

    override suspend fun addCar(sessionId: String?, brandId: Int, modelId: Int, carNumber: String?): me.rocketwash.client.data.dto.CarsAttributes {
        if (sessionId.isNullOrEmpty()) {
            throw UnauthorizedException()
        }
        val result = apiMapper.mapResponse(apiRocketWashCoroutines
                .addCar(sessionId!!,
                        brandId.toString(),
                        modelId.toString(),
                        carNumber ?: "")
                .await())

        return result.data
    }

}