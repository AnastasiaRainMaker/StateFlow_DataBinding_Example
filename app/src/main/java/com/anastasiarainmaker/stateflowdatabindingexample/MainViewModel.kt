package com.anastasiarainmaker.stateflowdatabindingexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel constructor(private val repo: MainRepository) :
    ViewModel() {

    private val _names = MutableStateFlow<String?>(null)
    val names: StateFlow<String?> = _names
    val addedName = MutableStateFlow<String?>(null)

    /**
     * Loading names from the repo async on the background thread and updating the StateFlow
     * ether with data received in the case of success or an error message.
     */
    fun loadFriendsNames() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.loadFriendsNames()
                    .combine(addedName) { listFromDataSource, addedName ->
                        if (addedName.isNullOrEmpty())
                            listFromDataSource
                        else "$listFromDataSource, $addedName"
                    }
                    .flowOn(Dispatchers.IO)
                    .collect {
                        println("I'm working in thread ${Thread.currentThread().name}")
                        _names.value = it
                    }
            } catch (ex: Exception) {
                _names.value = "Something went wrong"
            }
        }

    }
}