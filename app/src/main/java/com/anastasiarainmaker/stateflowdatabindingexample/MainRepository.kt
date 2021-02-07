package com.anastasiarainmaker.stateflowdatabindingexample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository {

    fun loadFriendsNames(): Flow<String> = flow {
        emit(ApiProvider().getNamesFromRemote().joinToString())
    }
}

class ApiProvider {

    fun getNamesFromRemote() = listOf("Ana", "Mary", "James", "Edward")
}