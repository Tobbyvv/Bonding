package com.alice.teampang.src.main.`when`.service

import com.alice.teampang.services.Services
import com.alice.teampang.src.GlobalApplication
import com.alice.teampang.src.error.ErrorUtils
import com.alice.teampang.src.main.`when`.interfaces.WhenFragView
import com.alice.teampang.src.main.`when`.model.WhenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WhenService(whenFragView: WhenFragView) {

    private val mWhenFragView : WhenFragView = whenFragView

    fun getWhen() {
        val whenRetrofitInterface : Services =
            GlobalApplication.getRetrofit()!!.create(
                Services::class.java
            )

        whenRetrofitInterface.getWhen().enqueue(object  : Callback<WhenResponse>{
            override fun onResponse(call: Call<WhenResponse>, response: Response<WhenResponse>) {
                val WhenResponse: WhenResponse? = response.body()
                val error: ResponseBody? = response.errorBody()
                if (WhenResponse == null) {
                    if (error != null) mWhenFragView.WhenError(ErrorUtils.parseError(error))
                    else mWhenFragView.WhenFailure(null)
                    return
                }
                mWhenFragView.WhenSuccess(WhenResponse)
            }

            override fun onFailure(call: Call<WhenResponse>, t: Throwable) {
                mWhenFragView.WhenFailure(t)
            }

        }
        )}
    }




