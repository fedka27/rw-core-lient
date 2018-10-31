package me.rocketwash.client.data.repository.user

import me.rocketwash.client.data.dto.sign_in.SignIn

//todo refactor to separate interactor
interface UserRepository {
    /**
     * Sign in user
     * @param signIn data to sign in
     * */
    suspend fun signIn(signIn: SignIn): me.rocketwash.client.data.dto.sign_in.LoginData

    suspend fun tryRegisterDevice(sessionId: String,
                                  playerId: String)

    suspend fun sendPinCode(phone: String): Boolean

    suspend fun createEmptyUser(): me.rocketwash.client.data.dto.Profile

    suspend fun changePhone(sesstionId: String, phone: String): me.rocketwash.client.data.dto.Profile

    suspend fun registerUser(phone: String): me.rocketwash.client.data.dto.Profile

    suspend fun activateProfile(profile: me.rocketwash.client.data.dto.Profile, pinCode: String): Boolean

    suspend fun addCar(sessionId: String?, brandId: Int, modelId: Int, carNumber: String?): me.rocketwash.client.data.dto.CarsAttributes
}