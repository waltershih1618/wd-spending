package com.wd.spending.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.wd.spending.data.SpendingRepository
import com.wd.spending.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: SpendingRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _userAuthIOException = SingleLiveEvent<UserRecoverableAuthIOException>()
    val userAuthIOException = _userAuthIOException

    fun getPaymentAndSpend() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.fetchPaymentAndSpend().flowOn(Dispatchers.IO).collect {
                    _text.postValue(it.toString())
                }
            } catch (e: UserRecoverableAuthIOException) {
                _userAuthIOException.postValue(e)
            }
        }
    }
}