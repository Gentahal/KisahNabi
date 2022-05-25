package com.genta.kisahnabi.ui

import android.widget.TableRow
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.genta.kisahnabi.data.KisahResponse
import com.genta.kisahnabi.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.logging.Handler

class MainViewModel : ViewModel() { //transsaksi data (layer data), sambungan dari data ke layer

    val kisahResponse = MutableLiveData<List<KisahResponse>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    private fun getKisahNabi(responHandler: (List<KisahResponse>)-> Unit, errorHandler: (Throwable) -> Unit ) {
        ApiClient.getApiService().getKisahNabi()
            .subscribeOn(Schedulers.io()) //asycronus
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it) //get data
            },{
                errorHandler(it)
            })
    }

    fun getData(){
        isLoading.value = true
        getKisahNabi({
            isLoading.value = false
            kisahResponse.value = it
        },{
            isLoading.value = true
            isError.value = it
        } )
    }
}