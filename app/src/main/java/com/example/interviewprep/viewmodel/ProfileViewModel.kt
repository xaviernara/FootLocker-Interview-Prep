package com.example.interviewprep.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewprep.model.ProfilePage
import com.example.interviewprep.repo.ProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepo) : ViewModel() {

    private var _profile = MutableStateFlow<List<ProfilePage>>(emptyList())
    var profile = _profile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        loadProfiles()
    }

    private fun loadProfiles() {
        viewModelScope.launch {
            val response = repository.getProfileFromApi()
            _isLoading.value = true
            _error.value = null

            try {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    response.body()?.let {
                        repository.insertProfileToDb(it)
                        repository.getProfileFromDb().collectLatest { profiles ->
                            _profile.value = profiles
                        }
                    }
                } else {
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                _error.value = e.message
                //fallback: try to load from db if api fails
                repository.getProfileFromDb().collectLatest { profiles ->
                    _profile.value = profiles
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun insertProfile(profilePage: ProfilePage) {
        try {
            viewModelScope.launch {
                repository.insertProfileToDb(profilePage)
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }
}